package com.github.nanoyou.maidnana.dao;

import com.github.nanoyou.maidnana.entity.Template;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

public class TemplateDao {
    @Getter
    private final static TemplateDao instance = new TemplateDao();

    /**
     * @param t
     * @return
     */
    public Template add(Template t) {
        // TODO:
        return null;
    }

    /**
     * @param uuid
     * @return
     */
    public Template get(UUID uuid) {
        // TODO:
        return null;
    }

    /**
     * @param t
     * @return
     */
    public Template modify(Template t) {
        // TODO:
        return null;
    }

    /**
     * @param uuid
     * @return
     */
    public Template delete(UUID uuid) {
        // TODO:
        return null;
    }

    /**
     * @return
     */
    public List<Template> getAll() {
        // TODO:
        return null;
    }

}
