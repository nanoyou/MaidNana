package com.github.nanoyou.maidnana.entity;

import lombok.Data;

import java.util.Map;
import java.util.UUID;

/**
 * Entity - TemplateBody
 */
@Data
public class TemplateBody implements Body{
    private UUID templateID;
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
     * name = Gay ♂ 率论
     * meeting_number = 1145-5141-9198
     *
     * 结果为:
     *
     * ------[上课提醒]------
     * 课程名称: Gay ♂ 率论
     *
     * 腾讯会议号: 1145-5141-9198 (这么臭的会议号还有存在的必要么（恼）)
     * ---------------------
     * @return The body string.
     */
    @Override
    public String getBodyString() {
        // TODO 将 template 所有值替换后返回
        return null;
    }
}
