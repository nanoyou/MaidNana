package com.github.nanoyou.maidnana.entity;

import lombok.Data;

/**
 * Entity - MinuteTrigger
 *
 * @author Huang Samuel
 * @date 2022/08/31
 */
@Data
public class MinuteTrigger extends Trigger{
    private int interval;
}
