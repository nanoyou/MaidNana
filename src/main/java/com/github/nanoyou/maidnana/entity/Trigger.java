package com.github.nanoyou.maidnana.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity - Trigger
 *
 * @author Huang Samuel
 */
@Getter
public class Trigger implements Identifiable {
    private UUID uuid;
    private String cron;
}
