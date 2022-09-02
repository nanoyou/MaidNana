package com.github.nanoyou.maidnana.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

/**
 * Entity - Template
 *
 * @author Huang Samuel
 */
@Getter
@AllArgsConstructor
public class Template implements Identifiable{
    private UUID uuid;
    private String template;
}
