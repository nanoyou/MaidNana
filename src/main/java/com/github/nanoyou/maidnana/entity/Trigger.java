package com.github.nanoyou.maidnana.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity - Trigger
 *
 * @author Huang Samuel
 */
@Data
public class Trigger implements Identifiable {
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    private String cron;
}
