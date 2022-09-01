package com.github.nanoyou.maidnana.controller;

import com.github.nanoyou.maidnana.entity.Announcement;
import com.github.nanoyou.maidnana.service.AnnouncementService;
import net.mamoe.mirai.event.events.FriendMessageEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AnnouncementController {
    private static final AnnouncementController instance = new AnnouncementController();

    private Map<Long, Announcement> selectedAnnouncement = new HashMap<>();

    public static AnnouncementController getInstance() {
        return instance;
    }

    public void newAnnouncement(FriendMessageEvent event) {
        if (!event.getMessage().contentToString().startsWith("新建公告")) {
            return;
        }
        event.getSender().sendMessage("新建公告!");
    }
    public void selectAnnouncement(FriendMessageEvent event) {
        if (!event.getMessage().contentToString().startsWith("选择公告")) {
            return;
        }
        String[] line = event.getMessage().contentToString().split(" ");
        if (line.length < 2) {
            event.getSender().sendMessage("命令格式: 选择公告 <公告UUID>");
            return;
        }
        UUID announcementUUID;
        try {
            announcementUUID = UUID.fromString(line[1]);
        } catch (IllegalArgumentException e) {
            event.getSender().sendMessage("错误的 UUID");
            return;
        }

        var a = AnnouncementService.getInstance().get(announcementUUID);
        if (a.isEmpty()) {
            event.getSender().sendMessage("未找到公告");
            return;
        }
        selectedAnnouncement.put(event.getSender().getId(), a.get());
        event.getSender().sendMessage("选择公告: " + line[1]);
    }
}
