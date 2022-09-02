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
        EventChannel<Event> eventChannel = GlobalEventChannel.INSTANCE.parentScope(this);
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
        channel.subscribeAlways(FriendMessageEvent.class, announcementController::selectAnnouncement);
    }
}
