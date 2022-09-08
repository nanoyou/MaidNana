package com.github.nanoyou.maidnana.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity - Trigger
 */
@Data
public class Trigger implements Identifiable {
    private UUID uuid;

    private String cron;
}
