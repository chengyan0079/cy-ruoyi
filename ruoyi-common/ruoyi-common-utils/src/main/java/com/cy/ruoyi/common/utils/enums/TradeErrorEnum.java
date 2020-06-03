package com.cy.ruoyi.common.utils.enums;


/**
 * 交易错误枚举
 * 服务异常
 *
 * 一共 7 位，分成三段
 *
 * 第一段，1 位，类型
 *      1 - 系统级别异常
 *      2 - 业务级别异常
 * 第二段，3 位，系统类型
 *      001 - 网关服务
 *      002 - 鉴权服务
 *      003 - 用户服务
 *      004 - 工作流服务
 *      005 - 代码生成服务
 *      006 - 定时任务服务
 *      007 - 搜索服务
 *      010 - Demo provider服务
 *      011 - Demo consumer服务
 *      999 - 其他服务
 * 第四段，3 位，错误码
 *       不限制规则。
 *       一般建议，每个模块自增。
 */
public enum TradeErrorEnum {
    // 999 - 默认系统异常
    SYSTEM_DEFAULT_ERROR("1999001", "默认系统异常"),

    // 001 - 网关服务
    GATEWAY_REQUEST_FAIL("1001001", "网关执行请求失败，服务降级处理！"),

    GATEWAY_TOKEN_NULL("2001001", "Token不能为空！"),
    GATEWAY_TOKEN_ERROR("2001002", "Token验证失败！"),
    GATEWAY_IMGCODE_NULL("2001003", "验证码不能为空！"),
    GATEWAY_IMGCODE_ERROR("2001004", "验证码不合法！"),
    GATEWAY_IMGCODE_FAIL("2001005", "获取验证码异常！"),


    // 002 - 鉴权服务
    AUTH_REDIS_GET_ERROR("1002001", "Redis GET操作异常！"),
    AUTH_REDIS_SET_ERROR("1002002", "Redis SET操作异常！"),

    AUTH_USER_OR_PWD_NULL("2002001", "用户名或密码不能为空！"),
    AUTH_USER_PWD_MATCH_ERROR("2002002", "用户名和密码匹配失败！"),
    AUTH_USER_NOT_EXISTS("2002003", "用户名不存在！"),
    AUTH_USER_PWD_DELETE("2002004", "对不起，您的账号已被删除！"),
    AUTH_USER_BLOCKED("2002005", "该用户已封禁，请联系管理员！"),
    AUTH_PWD_ERROR("2002006", "密码错误！"),

    // 004 - 工作流服务
    ACTIVITI_IS_RUNNING("2004001", "流程正在运行中！"),
    ACTIVITI_NO_RECORD("2004002", "没有找到相关工作流！"),
    ACTIVITI_MODELER_NO_DATA("2004003", "模型数据为空，请先设计流程并成功保存，再进行发布！"),
    ACTIVITI_MODELER_ERROR("2004004", "数据模型不符要求，请至少设计一条主线流程！"),
    ACTIVITI_EXTENSION_ERROR("2004005", "不支持的文件格式！"),
    ACTIVITI_CREATE_ERROR("2004006", "工作流部署失败！"),
    ACTIVITI_PARAMS_NULL("2004007", "参数不能为空！"),
    ACTIVITI_CREATE_IMG_FAIL("2004008", "创建流程图片失败！"),

    // 005 - 代码生成服务
    GEN_CODE_FAIL("2005001", "渲染模板失败！"),
    GEN_TREE_NULL("2005002", "树编码字段不能为空！"),
    GEN_FATHER_TREE_NULL("2005003", "树父编码字段不能为空！"),
    GEN_TREE_NAME_NULL("2005004", "树名称字段不能为空！"),

    // 003 - 用户服务
    USER_FILE_DOWNLOAD_FAIL("2003001", "下载文件失败！"),
    USER_FILENAME_ERROR("2003002", "文件名称非法，不允许下载！"),
    USER_ADD_USERNAME_REPEAT_ERROR("2003003", "新增用户失败，登录账号已存在！"),
    USER_ADD_PHONE_REPEAT_ERROR("2003004", "新增用户失败，手机号码已存在！"),
    USER_ADD_MAIL_REPEAT_ERROR("2003005", "新增用户失败，邮箱账号已存在！"),
    USER_IS_ADMIN("2003006", "不允许修改超级管理员用户！"),
    USER_UPDATE_PHONE_REPEAT_ERROR("2003007", "修改用户失败，手机号码已存在！"),
    USER_UPDATE_MAIL_REPEAT_ERROR("2003008", "修改用户失败，邮箱账号已存在！"),
    USER_DATA_IMPORT_NULL("2003009", "导入用户数据不能为空！"),
    USER_DEPT_LOCK("2003010", "该部门已停用！"),

    // 007 - 搜索服务
    SEARCH_INDEX_NULL("2007001", "索引不能为空！"),
    SEARCH_CREATE_INDEX_ERROR("2007002", "该索引已存在，不能创建！"),
    SEARCH_DELETE_INDEX_ERROR("2007003", "该索引不存在，不能删除！"),
    SEARCH_PARAMS_NULL("2007004", "参数不能为空！"),
    SEARCH_CREATE_DOC_ERROR("2007005", "该索引为空，添加文档失败！"),
    SEARCH_QUERY_PARAMS_NULL("2007006", "搜索条件不能为空！"),
    SEARCH_CREATE_FAIL("2007007", "创建失败！"),
    SEARCH_DELETE_INDEX_FAIL("2007008", "删除索引失败！"),
    SEARCH_QUERY_FAIL("2007009", "查询失败！"),
    SEARCH_CREATE_DOC_FAIL("2007010", "添加文档失败！"),
    SEARCH_DELETE_DOC_FAIL("2007011", "删除文档失败！"),
    SEARCH_BATCH_CREATE_DOC_FAIL("2007012", "批量添加文档失败！"),


    ;

    public final String code;
    public final String msg;

    TradeErrorEnum(String mCode, String mMsg){
        code = mCode;
        msg = mMsg;
    }

    /**
     * 根据CODE值获取异常状态内容
     * @param mCode
     * @return
     */
    public static String getByCode(String mCode){
        for(TradeErrorEnum tradeErrorEnum: TradeErrorEnum.values()){
            if(mCode.equals(tradeErrorEnum.code) ){
                return tradeErrorEnum.msg;
            }
        }
        return "";
    }

}
