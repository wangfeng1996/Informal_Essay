package com.sinocarbon.polaris.commons.utils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.sinocarbon.polaris.commons.pojo.LoginAppUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PublicCommonsUtils {

	/**
	 * 获取当前登录对象姓名
	 * 
	 * @return
	 */
	public static String getLoginUserName() throws RuntimeException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getName() != null && !"anonymousUser".equals(authentication.getName())) {
			return authentication.getName();
		} else {
			return null;
		}
	}

	/**
	 * 获取当前登录对象
	 * 
	 * @return
	 */
	public static LoginAppUser getLoginAppUser() {
		LoginAppUser loginAppUser;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof OAuth2Authentication) {
			OAuth2Authentication oAuth2Auth = (OAuth2Authentication) authentication;
			authentication = oAuth2Auth.getUserAuthentication();

			if (authentication instanceof UsernamePasswordAuthenticationToken) {
				UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
				loginAppUser = (LoginAppUser) authenticationToken.getPrincipal();
				loginAppUser.setUserPassword(null);
				return loginAppUser;
			} else if (authentication instanceof PreAuthenticatedAuthenticationToken) {
				// 刷新token方式
				PreAuthenticatedAuthenticationToken authenticationToken = (PreAuthenticatedAuthenticationToken) authentication;
				loginAppUser = (LoginAppUser) authenticationToken.getPrincipal();
				loginAppUser.setUserPassword(null);
				return loginAppUser;
			}
		}
		return null;
	}

	/**
	 * 获取请求用户的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	@SuppressWarnings("unchecked")
	public static <S, T> List<T> populateList(List<S> source, List<T> target, Class<?> targetClass) {
		for (int i = 0; i < source.size(); i++) {
			try {
				Object object = targetClass.newInstance();
				target.add((T) object);
				populate(source.get(i), object);
			} catch (Exception e) {
				log.error("dto和entity转换错误：{" + e.getMessage() + "}");
				continue;// 某个方法反射异常
			}
		}
		return target;
	}

	/**
	 * 
	 * 将dto和entity之间的属性互相转换,dto中属性一般为String等基本类型,
	 * 
	 * 但是entity中可能有复合主键等复杂类型,需要注意同名问题
	 * 
	 * @param src
	 * 
	 * @param target
	 * 
	 */

	public static Object populate(Object source, Object target) {
		Method[] srcMethods = source.getClass().getMethods();
		Method[] targetMethods = target.getClass().getMethods();
		for (Method m : srcMethods) {
			String srcName = m.getName();
			if (srcName.startsWith("get")) {
				try {
					Object result = m.invoke(source);
					for (Method mm : targetMethods) {
						String targetName = mm.getName();
						if (targetName.startsWith("set") && targetName.substring(3, targetName.length())
								.equals(srcName.substring(3, srcName.length()))) {
							mm.invoke(target, result);
						}
					}
				} catch (Exception e) {
					log.error("dto和entity转换错误：{" + e.getMessage() + "}");
				}
			}
		}
		return target;
	}

	public static String getNameUUIDFromBytes(String params) throws Exception {
		String uuid = UUID.nameUUIDFromBytes((params).getBytes()).toString();
		return uuid.substring(0, 13).replaceAll(Constant.HORIZONTAL_LINE, Constant.EMPTY);
	}

}
