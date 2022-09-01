package com.github.nanoyou.maidnana.service;

import com.github.nanoyou.maidnana.dao.AnnouncementDao;
import com.github.nanoyou.maidnana.dao.TemplateDao;
import com.github.nanoyou.maidnana.entity.Template;
import lombok.Getter;

import java.util.UUID;

public class TemplateService {

    @Getter
    private final static TemplateService instance = new TemplateService();

    private final AnnouncementDao announcementDao = AnnouncementDao.getInstance();

    private final TemplateDao templateDao = TemplateDao.getInstance();

    /**
     * @return
     */
    public Template create() {
        return null;
    }

    /**
     * @param templateID
     */
    public void delete(UUID templateID) {

    }

    /**
     * @param templateID
     */
    public void get(UUID templateID) {

    }

    /**
     * @param templateID
     * @param template
     */
    public void modify(UUID templateID, String template) {

    }

}
