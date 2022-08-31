package com.github.nanoyou.maidnana.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

/**
 * Entity - Template
 *
 * @author Huang Samuel
 * @date 2022/08/31
 */
@Getter
@AllArgsConstructor
public class Template {
    private UUID uuid;
    private String template;
}
