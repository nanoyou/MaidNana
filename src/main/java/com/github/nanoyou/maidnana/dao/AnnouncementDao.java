package com.github.nanoyou.maidnana.dao;

import com.github.nanoyou.maidnana.MaidNana;
import com.github.nanoyou.maidnana.entity.Announcement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.Collection;

public class AnnouncementDao extends AliasDao<Announcement> {
    private static AnnouncementDao instance = null;
    public static AnnouncementDao getInstance() {
        if (instance == null) {
            instance = new AnnouncementDao();
        }
        return instance;
    }

    @Override
    public Path getPath() {
        return MaidNana.INSTANCE.getDataFolderPath().resolve("announcements.json");
    }

    @Override
    public Type getType() {
        return new TypeToken<Collection<Announcement>>(){}.getType();
    }
}
