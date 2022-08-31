package com.github.nanoyou.maidnana.entity;

import lombok.Data;

import java.util.Map;

/**
 * Entity - TemplateBody
 *
 * @author Huang Samuel
 * @date 2022/08/31
 */
@Data
public class TemplateBody extends Body{
    private Template template;
    private Map<String, String> var;
}
