package com.github.nanoyou.maidnana.controller;

import com.github.nanoyou.maidnana.service.TemplateService;
import net.mamoe.mirai.event.events.FriendMessageEvent;

import java.util.UUID;

public class TemplateController {
    private static final TemplateController instance = new TemplateController();

    public static TemplateController getInstance() {
        return instance;
    }

    /**
     * 新建模板<br />
     * 命令格式:<br />
     * 新建模板<br />
     * &lt;模板体(多行)&gt;<br />
     *
     * 样例:<br />
     * 新建模板<br />
     * -------[上课通知]------<br />
     * 课程名称: $name$<br />
     * 腾讯会议: $tencent_meeting$<br />
     * -----------------------<br />
     */
    public void newTemplate(FriendMessageEvent event) {
        if (!event.getMessage().contentToString().startsWith("新建模板")) {
            return;
        }
        var template = event.getMessage().contentToString().replaceFirst("新建模板\n", "");
        if (template.equals("")) {
            event.getSender().sendMessage("模板不能为空!");
            return;
        }
        var result = TemplateService.getInstance().create(template);
        event.getSender().sendMessage("创建成功, UUID: " + result.getUuid().toString());
    }

    /**
     * 删除模板<br />
     * 命令格式:<br />
     * 删除模板 &lt;模板UUID&gt;
     *
     */
    public void deleteTemplate(FriendMessageEvent event) {
        if (!event.getMessage().contentToString().startsWith("删除模板")) {
            return;
        }
        var line = event.getMessage().contentToString().split(" ");
        if (line.length < 2) {
            event.getSender().sendMessage("命令格式错误, 用法: 删除模板 <模板UUID>");
            return;
        }
        UUID templateID;
        try {
            templateID = UUID.fromString(line[1]);
        } catch (IllegalArgumentException e) {
            event.getSender().sendMessage("命令格式错误, 用法: 删除模板 <模板UUID>");
            return;
        }
        TemplateService.getInstance().delete(templateID).ifPresentOrElse(
                r -> event.getSender().sendMessage("删除 " + line[1] + " 成功"),
                () -> event.getSender().sendMessage("模板未找到")
        );
    }
}
