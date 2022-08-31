package com.github.nanoyou.maidnana.entity;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Entity - MinuteTrigger
 *
 * @author Huang Samuel
 * @date 2022/08/31
 */
@Getter
@AllArgsConstructor
public class MinuteTrigger extends Trigger{
    private int interval;
}
