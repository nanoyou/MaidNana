package com.github.nanoyou.maidnana.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entity - Until
 *
 * @author Huang Samuel
 * @date 2022/08/31
 */
@Data
public class Until extends Expire{
    private LocalDateTime datetime;
}
