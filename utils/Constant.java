package com.sinocarbon.polaris.commons.utils;

public class Constant {
    /**
     * word 后缀
     */
    public static final String DOC_SUFFIX = ".doc";
    public static final String DOCX_SUFFIX = ".docx";
    /**
     * excel 后缀
     */
    public static final String EXCEL_SUFFIX = ".xls";
    public static final String EXCELX_SUFFIX = ".xlsx";
    /**
     * ppt 后缀
     */
    public static final String PPT_SUFFIX = ".ppt";
    public static final String PPTX_SUFFIX = ".pptx";

    public static final String BAT_SUFFIX = ".bat";
    public static final String SH_SUFFIX = ".sh";
    public static final String ENTER_WIN = "\r\n";
    public static final String ENTER_LIN = "\n";
    
    /**
     * md 后缀
     */
    public static final String MD_SUFFIX = ".md";
    
    public static final String SH = "sh";

    public static final Integer PAGE_SIZE = 10;
    public static final Integer PAGE_NUMBER_INIT = 1;
    public static final Integer NO_PAGE_NUMBER = -1;

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String FAIL = "fail";
    public static final String USER_SECRETCODE_ERROR = "用户名或密码错误！";
    public static final String USER_DISABLED = "该用户被冻结或关闭，无法登陆！请联系管理员！";
    public static final String USER_DOESNOT_EXIST = "用户名不存在！";
    public static final String USER_HAVE_LANDED = "该用户已登陆！";
    public static final String CAPTCHA_ERROR = "验证码错误！";
    public static final String LOGIN_SUCCESS = "登陆成功！";
    public static final String CACHE_FAILURE = "缓存失效或用户已退出登录！";
    public static final String SUCCESS_MESSAGE = "操作成功！";
    public static final String ADD_MESSAGE = "添加成功！";
    public static final String MODFILY_MESSAGE = "修改成功！";
    public static final String DELETE_MESSAGE = "删除成功！";
    public static final String REPEAT_MESSAGE = "数据不重复可正常操作！";
    public static final String FAIL_MESSAGE = "操作失败！请重试！";
 
    /**
     * 无效状态
     */
    public static final Integer INVSLID_FLAG = 0;
    /**
     * 成功状态
     */
    public static final Integer SUCCESS_FLAG = 1;
    /**
     * 删除状态
     */
    public static final Integer DELETE_FLAG = 2;
    /**
     * 新增操作
     */
    public static final Integer ADD_OPERATION_FLAG = 1;
    /**
     * 编辑操作
     */
    public static final Integer EDIT_OPERATION_FLAG = 2;
    /**
     * 开启状态
     */
    public static final Integer STATUS_ON = 1;
    /**
     * 关闭状态
     */
    public static final Integer STATUS_OFF = 2;
    
    /**
     * cookie的过期时间为-1，浏览器关闭后失效
     */
    public static final Integer COOKIE_EXPRE_TIME = -1;
    
    public static final String SLASH = "/";
    public static final String SPOT = ".";
    public static final String UNDERLINE = "_";
    public static final String HORIZONTAL_LINE = "-";
    public static final String SMALL = "small";
    public static final String EMPTY = "";
    public static final String BLANK_SPACE = " ";
    public static final String COMMA = ",";
    public static final String GREATER = ">";
    public static final String RIGHT_BRACKET = "(";
    
    public static final String ASC = "ascend";
    public static final String DESC = "descend";
    
    public static final String OPERATOR_NAME = "operatorName";
	public static final String OPERATOR_TYPE = "operatorType";
	public static final String OPLOG = "OPLOG";
	public static final String OPERATOR_LOG = "OPERATOR-LOG";
	public static final String OPERATRD_OBJECT = "operatedObject";
	public static final String OPERATRD_OBJECT_TYPE = "operatedObjectType";
	public static final String OPERATOR_TIME = "operatorTime";
	public static final String OPERATOR_IP = "operatorIP";
	
	public static final String LOG_TOPIC = "logTopic";
	public static final String DEFAULT_TOPIC = "audit-log";
	
	public static final String LOG_TENANTID = "logTenantId";
	
	/**
     * 日志用的常量（操作类型）
     */
    public static final String LOG_LOGIN = "登录";//登录
    public static final String LOG_LOGOUT = "登出";//登出
    public static final String LOG_ADD = "新增";//新增
    public static final String LOG_UPDATE = "更新";//更新
    public static final String LOG_DELETE = "删除";//删除
    public static final String LOG_UPLOAD = "上传";//上传
    public static final String LOG_EXPORT = "导出";//导出
    public static final String LOG_DOWNLOAD = "下载";//下载

}
