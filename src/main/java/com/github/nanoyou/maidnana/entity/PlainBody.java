package com.github.nanoyou.maidnana.entity;

import lombok.Data;

/**
 * Entity - PlainBody
 *
 * @author Huang Samuel
 */
@Data
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
