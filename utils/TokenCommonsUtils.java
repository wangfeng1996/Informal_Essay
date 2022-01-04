package com.sinocarbon.polaris.commons.utils;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenCommonsUtils {
	
	public static String extractToken(HttpServletRequest request) {
		String token = TokenCommonsUtils.extractCookieToken(request);
		if (token == null) {
			token = extractHeaderToken(request);
			if (token == null) {
				log.debug("Token not found in headers. Trying request parameters.");
				token = request.getParameter(OAuth2AccessToken.ACCESS_TOKEN);
				if (token == null) {
					log.debug("Token not found in request parameters.  Not an OAuth2 request.");
					return null;
				} else {
					return token;
				}
			} else {
				log.info("Token found in headers.");
				return token;
			}
		} else {
			log.info("Token found in Cookie.");
			return token;
		}

	}

	private static String extractCookieToken(HttpServletRequest request) {
		Cookie token = CookieUtils.get(request, CookieUtils.ACCESS_TOKEN);
		if (token != null) {
			request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, OAuth2AccessToken.BEARER_TYPE);
			return token.getValue();
		} else {
			return null;
		}
	}

	private static String extractHeaderToken(HttpServletRequest request) {
		Enumeration<String> headers = request.getHeaders("Authorization");
		while (headers.hasMoreElements()) {
			String value = headers.nextElement();
			if ((value.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase()))) {
				String authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
				request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE,
						value.substring(0, OAuth2AccessToken.BEARER_TYPE.length()).trim());
				int commaIndex = authHeaderValue.indexOf(',');
				if (commaIndex > 0) {
					authHeaderValue = authHeaderValue.substring(0, commaIndex);
				}
				return authHeaderValue;
			}
		}
		return null;
	}

}
