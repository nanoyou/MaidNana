package com.github.nanoyou.maidnana;

import com.github.nanoyou.maidnana.controller.AnnouncementController;
import kotlin.Lazy;
import kotlin.LazyKt;
import net.mamoe.mirai.console.permission.*;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;


/**
 * 使用 Java 请把
 * {@code /src/main/resources/META-INF.services/net.mamoe.mirai.console.plugin.jvm.JvmPlugin}
 * 文件内容改成 {@code org.example.mirai.plugin.JavaPluginMain} <br/>
 * 也就是当前主类全类名
 *
 * 使用 Java 可以把 kotlin 源集删除且不会对项目有影响
 *
 * 在 {@code settings.gradle.kts} 里改构建的插件名称、依赖库和插件版本
 *
 * 在该示例下的 {@link JvmPluginDescription} 修改插件名称，id 和版本等
 *
 * 可以使用 {@code src/test/kotlin/RunMirai.kt} 在 IDE 里直接调试，
 * 不用复制到 mirai-console-loader 或其他启动器中调试
 */

public final class MaidNana extends JavaPlugin {
    public static final MaidNana INSTANCE = new MaidNana();
    // 公告的权限节点
    private final Lazy<Permission> announcementPermission = LazyKt.lazy(() -> {
        try {
            return PermissionService.getInstance().register(
                    permissionId("announcement"),
                    "公告相关权限",
                    getParentPermission()
            );
        } catch (PermissionRegistryConflictException e) {
            throw new RuntimeException(e);
        }
    });
    // 测试是否用户有权限
    public boolean hasAnnouncementPermission(User user) {
        PermitteeId pid;
        if (user instanceof Member) {
            pid = new AbstractPermitteeId.ExactMember(((Member) user).getGroup().getId(), user.getId());
        } else {
            pid = new AbstractPermitteeId.ExactUser(user.getId());
        }
        return PermissionService.hasPermission(pid, announcementPermission.getValue());
    }
    private MaidNana() {
        super(new JvmPluginDescriptionBuilder("com.github.nanoyou.maidnana", "0.1.0")
                .info("EG")
                .build());
    }

    @Override
    public void onEnable() {
        EventChannel<Event> eventChannel = GlobalEventChannel.INSTANCE.parentScope(this);
        eventChannel.subscribeAlways(GroupMessageEvent.class, g -> {
            //监听群消息
            getLogger().info(g.getMessage().contentToString());
            if (g.getGroup().getId() == 815997069L) {
                String msg = g.getMessage().contentToString();
                if (msg.startsWith("echo ")) {
                    g.getGroup().sendMessage(msg.replaceFirst("echo ", ""));
                }
            }
        });
        AnnouncementController announcementController = AnnouncementController.getInstance();

        // 过滤出所有有权限用户发的的消息
        EventChannel<Event> channel = eventChannel.filter(evt -> {
            if (!(evt instanceof FriendMessageEvent)) {
                return false;
            }
            return hasAnnouncementPermission(((FriendMessageEvent) evt).getSender());
        });

        // 注册消息
        channel.subscribeAlways(FriendMessageEvent.class, announcementController::newAnnouncement);
    }
}
