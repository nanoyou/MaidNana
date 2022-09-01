package com.github.nanoyou.maidnana.service;

import com.github.nanoyou.maidnana.dao.TemplateDao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.TestOnly;

public class AnnouncementService {
    @Getter
    private final static AnnouncementService instance = new AnnouncementService();

    private final TemplateDao templateDao = TemplateDao.getInstance();

    public void test() {

    }

}
