package com.github.nanoyou.maidnana.service;

import com.github.nanoyou.maidnana.dao.AnnouncementDao;
import com.github.nanoyou.maidnana.dao.TemplateDao;
import com.github.nanoyou.maidnana.entity.Announcement;
import com.github.nanoyou.maidnana.entity.Body;
import com.github.nanoyou.maidnana.entity.Trigger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.TestOnly;

import java.util.Optional;
import java.util.UUID;

public class AnnouncementService {
    private final static AnnouncementService instance = new AnnouncementService();
    public static AnnouncementService getInstance() {
        return instance;
    }

    /**
     * @return
     */
    public Announcement create() {
        // TODO:
        return null;
    }

    /**
     * 获取公告
     * @param announcementID ID
     * @return 找到返回值, 未找到返回空
     */
    public Optional<Announcement> get(UUID announcementID) {
        return Optional.empty();
    }

    /**
     * 获取公告
     * @param alias 别名
     * @return 找到返回值, 未找到返回空
     */
    public Optional<Announcement> get(String alias) {
        return Optional.empty();
    }
    /**
     * @param announcementID
     * @return
     */
    public Announcement delete(UUID announcementID) {
        return null;
    }

    /**
     * @param announcementID
     * @param groupID
     */
    public void addGroup(UUID announcementID, long groupID) {

    }

    /**
     * @param announcementID
     * @param groupID
     */
    public void removeGroup(UUID announcementID, long groupID) {

    }

    /**
     * @param announcementID
     * @param trigger
     */
    public void addTrigger(UUID announcementID, Trigger trigger) {

    }

    /**
     * @param announcementID
     * @param triggerID
     */
    public void removeTrigger(UUID announcementID, UUID triggerID) {

    }

    /**
     * @param announcementID
     * @param triggerID
     */
    public void increaseTrigger(UUID announcementID, UUID triggerID) {

    }

    /**
     * @param announcementID
     * @param body
     */
    public void setBody(UUID announcementID, Body body) {

    }

    /**
     * @param announcementID
     */
    public void enable(UUID announcementID) {

    }

    /**
     * @param announcementID
     */
    public void disable(UUID announcementID) {

    }
}
