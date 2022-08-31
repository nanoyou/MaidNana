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
 * @date 2022/08/31
 */
@Getter
public abstract class Trigger {
    private UUID uuid;
    private LocalDateTime startTime;
    private Expire expire;
}
