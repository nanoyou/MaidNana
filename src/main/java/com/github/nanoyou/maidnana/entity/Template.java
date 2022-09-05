package com.github.nanoyou.maidnana.entity;


import lombok.Data;

import java.util.UUID;

/**
 * Entity - Template
 */
@Data
public class Template implements Aliasable{
    private UUID uuid;
    private String alias;

    private String template;
}
