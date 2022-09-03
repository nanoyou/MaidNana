package com.github.nanoyou.maidnana.controller;

import com.github.nanoyou.maidnana.constant.Usage;
import com.github.nanoyou.maidnana.entity.Template;
import com.github.nanoyou.maidnana.service.TemplateService;
import net.mamoe.mirai.event.events.FriendMessageEvent;

import java.util.Optional;
import java.util.UUID;

public class TemplateController {
    private static final TemplateController instance = new TemplateController();

    public static TemplateController getInstance() {
        return instance;
    }

    private Optional<Template> getTemplate(String uuidOrAlias) {
        try {
            var uuid = UUID.fromString(uuidOrAlias);
            return TemplateService.getInstance().get(uuid);
        } catch (IllegalArgumentException e) {
            return TemplateService.getInstance().get(uuidOrAlias);
        }
    }


    /**
     * 新建模板<br />
     *
     * 样例:<br />
     * 新建模板 上课通知<br />
     * -------[上课通知]------<br />
     * 课程名称: $name$<br />
     * 腾讯会议: $tencent_meeting$<br />
     * -----------------------<br />
     */
    public void newTemplate(FriendMessageEvent event) {
        if (!event.getMessage().contentToString().startsWith("新建模板")) {
            return;
        }
        var lines = event.getMessage().contentToString().split("\n", 2);
        if (lines.length < 2) {
            event.getSender().sendMessage("命令格式错误, 用法:\n" + Usage.NEW_TEMPLATE);
            return;
        }
        var alias = lines[0].replaceFirst("新建模板\\s*", "");
        var template = lines[1];
        if (template.equals("")) {
            event.getSender().sendMessage("模板不能为空!");
            return;
        }

        Template result = null;
        if ("".equals(alias)) {
            result = TemplateService.getInstance().create(template);
        } else {
            var t = TemplateService.getInstance().create(template, alias);
            if (t.isEmpty()) {
                event.getSender().sendMessage("创建失败, 别名" + alias + "已存在");
                return;
            }
            result = t.get();
        }
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
            event.getSender().sendMessage("命令格式错误, 用法:\n" + Usage.DELETE_TEMPLATE);
            return;
        }
        UUID templateID;
        try {
            templateID = UUID.fromString(line[1]);
        } catch (IllegalArgumentException e) {
            event.getSender().sendMessage("命令格式错误, 用法:\n" + Usage.DELETE_TEMPLATE);
            return;
        }
        TemplateService.getInstance().delete(templateID).ifPresentOrElse(
                r -> event.getSender().sendMessage("删除 " + line[1] + " 成功"),
                () -> event.getSender().sendMessage("模板未找到")
        );
    }

    /**
     * 修改模板<br />
     * 命令格式:<br />
     * 修改模板 &lt;模板UUID | 模板别名&gt;
     * &lt;模板体(多行)&gt;
     *
     */
    public void modifyTemplate(FriendMessageEvent event) {
        if (!event.getMessage().contentToString().startsWith("修改模板")) {
            return;
        }
        var lines = event.getMessage().contentToString().split("\n", 2);
        if (lines.length < 2) {
            event.getSender().sendMessage("命令格式错误, 用法:\n" + Usage.MODIFY_TEMPLATE);
            return;
        }
        var uuidStr= lines[0].replaceFirst("修改模板 ", "");
        var body = lines[1];
        if (body.equals("")) {
            event.getSender().sendMessage("模板体不能为空");
            return;
        }
        UUID templateID;
        try {
            templateID = UUID.fromString(uuidStr);
        } catch (IllegalArgumentException e) {
            event.getSender().sendMessage("命令格式错误, 用法:\n" + Usage.MODIFY_TEMPLATE);
            return;
        }
        TemplateService.getInstance().modify(templateID, body).ifPresentOrElse(
                r -> event.getSender().sendMessage("修改" + r.getUuid().toString() + "成功"),
                () -> event.getSender().sendMessage("模板未找到")
        );

    }
}
