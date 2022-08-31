package com.github.nanoyou.maidnana.entity;


import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * Entity - Announcement
 *
 * @author Huang Samuel
 * @date 2022/08/31
 */
@Data
public class Announcement {
    private UUID uuid;
    private List<Long> groups;
    private Body body;
    private List<Trigger> triggers;
}
