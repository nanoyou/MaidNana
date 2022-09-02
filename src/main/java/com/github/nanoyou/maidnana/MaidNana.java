package com.github.nanoyou.maidnana;

import com.github.nanoyou.maidnana.controller.AnnouncementController;
import com.github.nanoyou.maidnana.controller.TemplateController;
import com.github.nanoyou.maidnana.dao.AnnouncementDao;
import com.github.nanoyou.maidnana.dao.BaseDao;
import com.github.nanoyou.maidnana.dao.TemplateDao;
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

import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Consumer;


/**
 * MaidNana 插件主类
 *
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
        initChannels();
        initFiles();
    }

    /**
     * 初始化消息频道
     */
    private void initChannels() {
        EventChannel<Event> eventChannel = GlobalEventChannel.INSTANCE.parentScope(this);
        var announcementController = AnnouncementController.getInstance();
        var templateController = TemplateController.getInstance();

        // 过滤出所有有权限用户发的的消息
        EventChannel<Event> channel = eventChannel.filter(evt -> {
            if (!(evt instanceof FriendMessageEvent)) {
                return false;
            }
            return hasAnnouncementPermission(((FriendMessageEvent) evt).getSender());
        });

        // 注册消息
        channel.subscribeAlways(FriendMessageEvent.class, announcementController::newAnnouncement);
        channel.subscribeAlways(FriendMessageEvent.class, announcementController::selectAnnouncement);
        channel.subscribeAlways(FriendMessageEvent.class, templateController::newTemplate);
    }
    // 初始化文件
    private void initFiles() {
        if (Files.notExists(getDataFolderPath())) {
            try {
                getLogger().info("创建数据文件夹");
                Files.createDirectories(getDataFolderPath());
            } catch (IOException e) {
                getLogger().error("创建数据文件夹失败", e);
                throw new RuntimeException(e);
            }
        }
    }
}
