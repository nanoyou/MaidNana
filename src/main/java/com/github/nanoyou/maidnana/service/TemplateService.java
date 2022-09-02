package com.github.nanoyou.maidnana.service;

import com.github.nanoyou.maidnana.dao.AnnouncementDao;
import com.github.nanoyou.maidnana.dao.TemplateDao;
import com.github.nanoyou.maidnana.entity.Template;
import lombok.Getter;

import java.util.UUID;

public class TemplateService {

    private final static TemplateService instance = new TemplateService();

    public static TemplateService getInstance() {
        return instance;
    }

    /**
     * 创建模板
     * @return 新建的模板
     */
    public Template create(String template) {
        var t = new Template();
        t.setUuid(UUID.randomUUID());
        t.setTemplate(template);
        TemplateDao.getInstance().add(t);
        return t;
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
