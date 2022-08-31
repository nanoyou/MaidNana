package com.github.nanoyou.maidnana.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * Entity - PlainBody
 *
 * @author Huang Samuel
 * @date 2022/08/31
 */
@Getter
@AllArgsConstructor
public class PlainBody implements Body{
    private String content;

    /**
     * @return The body string.
     */
    @Override
    public String getBodyString() {
        return null;
    }
}
