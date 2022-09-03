package com.github.nanoyou.maidnana.constant;

public class Usage {
    public static final String USAGE = "MaidNana - ver " + MaidNanaConstant.VERSION + "\n命令列表如下, [] 代表可选参数, <> 代表必选参数, - 后为使用说明";
    public static final String NEW_ANNOUNCEMENT = "新建公告 [公告别名] - 新建公告, 可指定别名便于记忆";
    public static final String SELECT_ANNOUNCEMENT = "选择公告 <公告UUID | 公告别名> - 为后续命令指定公告";
    public static final String NEW_TEMPLATE = "新建模板 [模板别名]\n<模板体(多行)> - 新建公告模板, 使用'$'定义变量, 如$name$";
    public static final String DELETE_TEMPLATE = "删除模板 <模板UUID | 模板别名> - 删除公告模板";
    public static final String MODIFY_TEMPLATE = "修改模板 <模板UUID | 模板别名>\n<模板体(多行)> - 修改指定的模板";
}
