package com.github.nanoyou.maidnana.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Entity - Until
 *
 * @author Huang Samuel
 * @date 2022/08/31
 */
@Getter
@AllArgsConstructor
public class Until implements Expire{
    private LocalDateTime datetime;

    @Override
    public boolean isExpired() {
        return false;
    }
}
