package com.github.nanoyou.maidnana.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Map;

/**
 * Entity - TemplateBody
 *
 * @author Huang Samuel
 */
@Getter
@AllArgsConstructor
public class TemplateBody implements Body{
    private Template template;
    private Map<String, String> var;

    /**
     * 根据模板生成公告内容
     *
     * 例:
     * 模板为:
     * ------[上课提醒]------
     * 课程名称: $name$
     *
     * 腾讯会议号: $meeting_number$
     * ---------------------
     * 变量为:
     * name = 概率论
     * meeting_number = 114514
     *
     * 结果为:
     *
     * ------[上课提醒]------
     * 课程名称: 概率论
     *
     * 腾讯会议号: 114514
     * ---------------------
     * @return The body string.
     */
    @Override
    public String getBodyString() {
        // TODO 将 template 所有值替换后返回
        return null;
    }
}
