package com.github.nanoyou.maidnana.dao;

import com.github.nanoyou.maidnana.entity.Announcement;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

public class AnnouncementDao {
    @Getter
    private final static AnnouncementDao instance = new AnnouncementDao();

    /**
     * 增加一个Announcement
     *
     * @param a
     */
    public Announcement add(Announcement a) {
        // TODO: 增加一个Announcement
        return null;
    }

    /**
     * 通过uuid查找返回Announcement
     *
     * @param uuid
     * @return
     */
    public Announcement get(UUID uuid) {
        // TODO: 通过uuid查找返回Announcement
        return null;
    }

    /**
     * @param a
     * @return
     */
    public Announcement modify(Announcement a) {
        // TODO:
        return null;
    }

    /**
     * @param uuid
     * @return
     */
    public Announcement delete(UUID uuid) {
        // TODO:
        return null;
    }

    /**
     * @return
     */
    public List<Announcement> getAll() {
        // TODO:
        return null;
    }

}
