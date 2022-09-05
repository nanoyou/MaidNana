package com.github.nanoyou.maidnana.entity;

import com.github.nanoyou.maidnana.service.TemplateService;
import lombok.Data;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

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
        var service = TemplateService.getInstance();
        var template = service.get(templateID);
        if(template.isEmpty()) return "";

        var bodyString = template.get().getTemplate();
        var.forEach((k,v)->{
            bodyString.replaceAll("$"+k+"$",v);
        });
        return bodyString;
    }
}
