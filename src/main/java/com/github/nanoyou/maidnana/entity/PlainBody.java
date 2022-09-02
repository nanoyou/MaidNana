package com.github.nanoyou.maidnana.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * Entity - PlainBody
 *
 * @author Huang Samuel
 */
@Getter
@AllArgsConstructor
public class PlainBody implements Body{
    private String content;

    /**
     * 直接返回纯公告内容
     *
     * @return The body string.
     */
    @Override
    public String getBodyString() {
        return content;
    }
}
