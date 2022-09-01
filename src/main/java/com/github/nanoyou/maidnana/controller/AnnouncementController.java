package com.github.nanoyou.maidnana.controller;

import lombok.Getter;
import net.mamoe.mirai.console.permission.PermissionService;
import net.mamoe.mirai.event.events.FriendMessageEvent;

public class AnnouncementController {
    private static final AnnouncementController instance = new AnnouncementController();

    public static AnnouncementController getInstance() {
        return instance;
    }

    public void newAnnouncement(FriendMessageEvent event) {
        if (!event.getMessage().contentToString().startsWith("新建公告")) {
            return;
        }
        event.getSender().sendMessage("新建公告!");
    }
}
