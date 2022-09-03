package com.github.nanoyou.maidnana.service;

import com.github.nanoyou.maidnana.dao.TemplateDao;
import com.github.nanoyou.maidnana.entity.Template;

import java.util.Optional;
import java.util.UUID;

public class TemplateService {

    private final static TemplateService instance = new TemplateService();

    public static TemplateService getInstance() {
        return instance;
    }

    /**
     * 创建模板
     * @param template 模板文本
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
     * 删除模板
     * @param templateID 要删除的模板 ID
     * @return 删除成功返回被删除的模板, 失败返回空
     */
    public Optional<Template> delete(UUID templateID) {
        return TemplateDao.getInstance().delete(templateID);
    }

    /**
     * @param templateID
     */
    public void get(UUID templateID) {

    }

    /**
     * 修改模板
     * @param templateID 要修改的模板 ID
     * @param template 模板体
     * @return 修改成功返回被修改的模板, 失败返回空
     */
    public Optional<Template> modify(UUID templateID, String template) {
        var t = TemplateDao.getInstance().get(templateID);
        if (t.isEmpty()) {
            return t;
        }
        var v = t.get();
        v.setTemplate(template);
        return Optional.of(TemplateDao.getInstance().modify(v));
    }

}
