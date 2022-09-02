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
 */
@Getter
@AllArgsConstructor
public class Announcement implements Identifiable {
    private UUID uuid;
    private List<Long> groups;
    private Body body;
    private boolean enabled;
    private List<Trigger> triggers;
}
