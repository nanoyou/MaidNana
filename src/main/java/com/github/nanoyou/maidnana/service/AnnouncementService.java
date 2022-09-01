package com.github.nanoyou.maidnana.service;

import com.github.nanoyou.maidnana.dao.TemplateDao;
import com.github.nanoyou.maidnana.entity.Announcement;
import com.github.nanoyou.maidnana.entity.Body;
import com.github.nanoyou.maidnana.entity.Trigger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.TestOnly;

import java.util.UUID;

public class AnnouncementService {
    @Getter
    private final static AnnouncementService instance = new AnnouncementService();

    private final TemplateDao templateDao = TemplateDao.getInstance();

    /**
     * @return
     */
    public Announcement create() {
        // TODO:
        return null;
    }

    /**
     * @param announcementID
     * @return
     */
    public Announcement get(UUID announcementID) {
        return null;
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
