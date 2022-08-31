package com.github.nanoyou.maidnana.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

/**
 * Entity - Announcement
 *
 * @author Huang Samuel
 * @date 2022/08/31
 */
@Getter
@AllArgsConstructor
public class Announcement {
    private UUID uuid;
    private List<Long> groups;
    private Body body;
    private boolean enabled;
    private List<Trigger> triggers;
}
