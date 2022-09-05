package com.github.nanoyou.maidnana.entity;


import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * 公告
 * Entity - Announcement
 * @date 2022/09/05
 */
@Data
public class Announcement implements Aliasable {
    private UUID uuid;
    private String alias;

    private List<Long> groups;
    private Body body;
    private boolean enabled;
    private List<Trigger> triggers;
}
