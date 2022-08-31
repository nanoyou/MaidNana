package com.github.nanoyou.maidnana.entity;

import lombok.Data;

import java.time.LocalDateTime;
/**
 * Entity - Trigger
 *
 * @author Huang Samuel
 * @date 2022/08/31
 */
@Data
public abstract class Trigger {
    private LocalDateTime startTime;
    private Expire expire;
}
