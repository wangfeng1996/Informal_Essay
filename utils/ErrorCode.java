package com.sinocarbon.polaris.commons.utils;

public class ErrorCode {

	/**
	 * 缺少必要参数！
	 */
	public static final Integer CODE_200010 = 200010;
	public static final String CODE_200010_MSG = "缺少必要参数！";
	/**
	 * 无效的操作！请检查参数信息是否正确！
	 */
	public static final Integer CODE_200020 = 200020;
	public static final String CODE_200020_MSG = "无效的操作！请检查参数信息是否正确！";
	
	/**
	 * 添加或修改的对象已存在！
	 */
	public static final Integer CODE_200030 = 200030;
	public static final String CODE_200030_MSG = "添加或修改的对象已存在！";
	/**
	 * 添加或修改失败！请重新尝试或联系管理员！
	 */
	public static final Integer CODE_200040 = 200040;
	public static final String CODE_200040_MSG = "添加或修改失败！请重新尝试或联系管理员！";
	/**
	 * 导出失败！请重新尝试或联系管理员！
	 */
	public static final Integer CODE_200050 = 200050;
	public static final String CODE_200050_MSG = "导出失败！请重新尝试或联系管理员！";
	
	/**
	 * 用户名不存在
	 */
	public static final Integer CODE_300010 = 300010;
	public static final String CODE_300010_MSG = "当前机构用户不存在！";
	/**
	 * 用户名或密码错误！
	 */
    public static final Integer CODE_300020 = 300020;
    public static final String CODE_300020_MSG = "用户名或密码错误！";
    /**
	 * 该用户被冻结或关闭，无法登陆！请联系管理员！
	 */
    public static final Integer CODE_300030 = 300030;
    public static final String CODE_300030_MSG = "该用户被冻结或关闭，无法登陆！请联系管理员！";
    /**
	 * 同一账号不可重复登录！
	 */
    public static final Integer CODE_300040 = 300040;
    public static final String CODE_300040_MSG = "同一账号不可重复登录！";
    /**
	 * 获取当前用户信息失败！缓存失效或用户已退出登录！
	 */
    public static final Integer CODE_300050 = 300050;
    public static final String CODE_300050_MSG = "获取当前用户信息失败！缓存失效或用户已退出登录！";
    /**
	 * 当前系统没有账号登录，退出异常！
	 */
    public static final Integer CODE_300060 = 300060;
    public static final String CODE_300060_MSG = "当前系统没有账号登录，退出异常！";
    
    /**
	 * 当前用户未配置权限，无法登录！请联系管理员！
	 */
   public static final Integer CODE_300070 = 300070;
   public static final String CODE_300070_MSG = "当前用户未配置权限，无法登录！请联系管理员！";

}
