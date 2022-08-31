package com.github.nanoyou.maidnana.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Map;

/**
 * Entity - TemplateBody
 *
 * @author Huang Samuel
 * @date 2022/08/31
 */
@Getter
@AllArgsConstructor
public class TemplateBody implements Body{
    private Template template;
    private Map<String, String> var;

    /**
     * @return The body string.
     */
    @Override
    public String getBodyString() {
        return null;
    }
}
