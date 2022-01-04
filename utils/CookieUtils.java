package com.sinocarbon.polaris.commons.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CookieUtils {

	public static final String ACCESS_TOKEN = "access_token";

	/**
	 * 设置cookie
	 * 
	 * @param domain    作用域
	 * @param path      路径
	 * @param name      cookie的key
	 * @param value     cookie的值
	 * @param expreTime 过期时间
	 * @param response
	 */
	public static void set(String domain, String path, String name, String value, int expreTime,
			HttpServletResponse response) {
		Cookie cookie = new Cookie(name, value); // 设置cookie的key和value值
		cookie.setDomain(domain);
		cookie.setPath(path); // 路径
		cookie.setMaxAge(expreTime); // 过期时间
		response.addCookie(cookie); // 添加cookie
	}

	/**
	 * 根据名称获取cookie
	 * 
	 * @param request
	 * @param name    cookie的key
	 * @return
	 */
	public static Cookie get(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

	/**
	 * 根据key清除cookie的value值
	 * 
	 * @param domain   作用域名
	 * @param path     路径
	 * @param name     cookie的名称
	 * @param request
	 * @param response
	 */
	public static void clearCookieValue(String domain, String path, String name, HttpServletRequest request,
			HttpServletResponse response) {
		log.info("清除 {} 域名下的Cookie：{}", name);
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				cookie.setMaxAge(0);
				cookie.setValue(null);
				cookie.setPath(path);
				cookie.setDomain(domain);
				response.addCookie(cookie);
			}
		}
	}

}
