package com.github.nanoyou.maidnana.entity;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 公告
 * Entity - Announcement
 */
@Data
public class Announcement implements Aliasable {
    public Announcement() {
        groups = new ArrayList<>();
        triggers = new ArrayList<>();
    }
    private UUID uuid;
    private String alias;

    private List<Long> groups;
    private Body body;
    private boolean enabled;
    private List<Trigger> triggers;
}
