package com.github.nanoyou.maidnana.entity;


import lombok.Data;

import java.util.UUID;

/**
 * Entity - Template
 *
 * @author Huang Samuel
 */
@Data
public class Template implements Identifiable{
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    private String template;
}
