package com.github.nanoyou.maidnana.constant;

public class Usage {
    // MiscController
    public static final String HELP = "帮助 - 查看帮助列表";
    // AnnouncementController
    public static final String NEW_ANNOUNCEMENT = "新建公告 [公告别名] - 新建公告并自动选择, 可指定别名便于记忆";
    public static final String SELECT_ANNOUNCEMENT = "选择公告 <公告UUID | 公告别名> - 为后续命令指定公告";
    public static final String DELETE_ANNOUNCEMENT = "删除公告 - 删除公告*";
    public static final String LIST_ANNOUNCEMENTS = "公告列表 - 查看公告列表";
    public static final String SET_GROUP = "设置群 <群号1> [群号2]... - 设置公告发送的群, 可设置多个*";
    public static final String UNSET_GROUP = "取消群 <群号1> [群号2]... - 取消设置公告发送的群, 可取消多个*";
    public static final String SET_PLAIN_BODY = "纯文本公告\n<公告体(多行)> - 设置纯文本公告体, 若已存在则覆盖*";
    public static final String SET_TEMPLATE_BODY = "模板公告 <模板UUID | 模板别名>\n变量1=值1\n变量2=值2\n... - 设置模板公告体, 若已存在则覆盖*";
    public static final String ENABLE_ANNOUNCEMENT = "开启公告 - 开启定时公告的发布*";
    public static final String DISABLE_ANNOUNCEMENT = "禁用公告 - 暂停定时公告的发布*";
    public static final String NEW_TRIGGER = "新建触发器 <cron表达式> - 新建触发器*";
    public static final String DELETE_TRIGGER = "删除触发器 [触发器UUID]... - 删除触发器, 若不指定UUID则删除全部*";
    public static final String PREVIEW = "预览 - 预览公告变量替换后结果*";
    public static final String SET_VARIABLE = "设置变量\n变量1=值1\n变量2=值2\n... - 设置变量(仅支持模板公告体)*";
    public static final String UNSET_VARIABLE = "取消变量 <变量1> [变量2]... - 取消设置变量(仅支持模板公告体)*";
    public static final String SHOW_ANNOUNCEMENT = "查看公告 - 查看公告信息*";
    public static final String MANUAL_TRIGGER = "发送公告 - 手动发送公告*";
    // TemplateController
    public static final String NEW_TEMPLATE = "新建模板 [模板别名]\n<模板体(多行)> - 新建公告模板, 使用'$'定义变量, 如$name$";
    public static final String DELETE_TEMPLATE = "删除模板 <模板UUID | 模板别名> - 删除公告模板";
    public static final String MODIFY_TEMPLATE = "修改模板 <模板UUID | 模板别名>\n<模板体(多行)> - 修改指定的模板";
    public static final String SHOW_TEMPLATE = "查看模板 <模板UUID | 模板别名> - 查看模板信息";
    public static final String LIST_TEMPLATES = "模板列表 - 查看模板列表";
    // 帮助信息
    public static final String USAGE = "MaidNana - ver "
            + MaidNanaConstant.VERSION
            + "\n命令列表如下, [] 代表可选参数, <> 代表必选参数, ...代表多个参数, * 表示需要先进行公告选择, - 后为使用说明\n"
            + HELP + "\n"
            + NEW_ANNOUNCEMENT + "\n"
            + DELETE_ANNOUNCEMENT  + "\n"
            + LIST_ANNOUNCEMENTS + "\n"
            + SET_GROUP + "\n"
            + UNSET_GROUP + "\n"
            + SET_PLAIN_BODY + "\n"
            + SET_TEMPLATE_BODY + "\n"
            + ENABLE_ANNOUNCEMENT + "\n"
            + DISABLE_ANNOUNCEMENT + "\n"
            + PREVIEW + "\n"
            + SET_VARIABLE + "\n"
            + UNSET_VARIABLE + "\n"
            + SHOW_ANNOUNCEMENT + "\n"
            + MANUAL_TRIGGER + "\n"
            + NEW_TRIGGER + "\n"
            + DELETE_TRIGGER + "\n"
            + NEW_TEMPLATE + "\n"
            + DELETE_TEMPLATE + "\n"
            + MODIFY_TEMPLATE + "\n"
            + SHOW_TEMPLATE + "\n"
            + LIST_TEMPLATES;

}
