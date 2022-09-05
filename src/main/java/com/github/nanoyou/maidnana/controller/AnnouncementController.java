package com.github.nanoyou.maidnana.controller;

import com.github.nanoyou.maidnana.constant.Usage;
import com.github.nanoyou.maidnana.entity.Announcement;
import com.github.nanoyou.maidnana.service.AnnouncementService;
import net.mamoe.mirai.event.events.FriendMessageEvent;

import java.util.*;

public class AnnouncementController {
    private static final AnnouncementController instance = new AnnouncementController();

    private final Map<Long, Announcement> selectedAnnouncement = new HashMap<>();

    public static AnnouncementController getInstance() {
        return instance;
    }

    /**
     * 通过 UUID 或别名自动获取公告
     *
     * @param uuidOrAlias UUID 或别名
     * @return 公告
     */
    private Optional<Announcement> getAnnouncement(String uuidOrAlias) {
        try {
            var uuid = UUID.fromString(uuidOrAlias);
            return AnnouncementService.getInstance().get(uuid);
        } catch (IllegalArgumentException e) {
            return AnnouncementService.getInstance().get(uuidOrAlias);
        }
    }

    /**
     * 格式化公告, 将公告内容格式化为文本
     * @param announcement 公告
     * @return 格式化后的文本
     */
    private String formatAnnouncement(Announcement announcement) {
        return "";
    }

    /**
     * 获取该用户选择的公告, 若未选择或公告失效(已删除)会自动回复消息, 直接 return 即可<br />
     * 例:
     * ...
     * var announcement = getSelectedAnnouncement(event);
     * if (announcement.isEmpty()) {
     *     return;
     * }
     * ...
     * @param event 事件
     * @return 如果获取到返回公告, 已失效/未选择返回空
     */
    private Optional<Announcement> getSelectedAnnouncement(FriendMessageEvent event) {
        return Optional.empty();
    }

    /**
     * 新建一个公告
     *
     * @param event 好友信息事件
     */
    public void newAnnouncement(FriendMessageEvent event) {
        if (!event.getMessage().contentToString().startsWith("新建公告")) {
            return;
        }
        event.getSender().sendMessage("新建公告!");
    }

    /**
     * 选择已有公告
     *
     * @param event 好友信息事件
     */
    public void selectAnnouncement(FriendMessageEvent event) {
        if (!event.getMessage().contentToString().startsWith("选择公告")) {
            return;
        }
        String[] line = event.getMessage().contentToString().split(" ");
        if (line.length < 2) {
            event.getSender().sendMessage("命令格式错误, 用法:\n" + Usage.SELECT_ANNOUNCEMENT);
            return;
        }
        getAnnouncement(line[1]).ifPresentOrElse(
                a -> {
                    selectedAnnouncement.put(event.getSender().getId(), a);
                    if (a.getAlias() != null) {
                        event.getSender().sendMessage("选择公告: " + a.getAlias() + "(" + a.getUuid().toString() + ")");
                    } else {
                        event.getSender().sendMessage("选择公告: " + a.getUuid().toString());
                    }
                },
                () -> event.getSender().sendMessage("未找到公告")
        );
    }

    /**
     * 删除指定的公告。此方法会遍历所有选中的公告。
     *
     * @param event 好友信息事件
     */
    public void deleteAnnouncement(FriendMessageEvent event) {

        if (!event.getMessage().contentToString().startsWith("删除公告")) {
            return;
        }

        if (selectedAnnouncement.isEmpty()) {
            event.getSender().sendMessage("未选择任何公告");
        }

        selectedAnnouncement.forEach((k, v) -> {
            if (k.equals(event.getSender().getId())) {
                AnnouncementService.getInstance().delete(v.getUuid());
            }
        });

        event.getSender().sendMessage("已删除选定公告");
    }

    /**
     * 查看公告列表
     *
     * @param event
     */
    public void selectAnnouncements(FriendMessageEvent event) {

        if (!event.getMessage().contentToString().startsWith("公告列表")) {
            return;
        }

        AnnouncementService.getInstance().getAll(event.getSender().getId()).ifPresentOrElse(
                l -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append("您共有").append(l.size()).append("条公告。\n");
                    l.forEach(a -> {
                        sb.append("UUID  ").append(a.getUuid());
                    });
                    event.getSender().sendMessage(sb.toString());
                },
                () -> {
                    event.getSender().sendMessage("未找到任何公告");
                }
        );
    }

    /**
     * 设置指定群为公告接收方
     *
     * @param event
     */
    public void setGroupAnnouncement(FriendMessageEvent event) {
        if (!event.getMessage().contentToString().startsWith("设置群")) {
            return;
        }
        String[] line = event.getMessage().contentToString().split(" ");
        if (line.length < 2) {
            event.getSender().sendMessage("命令格式错误, 用法:\n" + Usage.SET_GROUP);
            return;
        }

        List<Long> groupIds = new ArrayList<>();

        try {
            for (int i = 1; i < line.length; i++) {
                groupIds.add(Long.valueOf(line[i]));
            }
        } catch (Exception e) {
            event.getSender().sendMessage("命令格式错误, 用法:\n" + Usage.SET_GROUP);
            return;
        }

        selectedAnnouncement.forEach(
                (k, v) -> {
                    groupIds.forEach(groupId -> AnnouncementService.getInstance().addGroup(v.getUuid(), groupId));
                }
        );


    }
}
