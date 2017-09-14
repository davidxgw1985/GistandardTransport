package com.gistandard.transport.tools.util;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 校验通用工具
 * 
 * @author ShengHao
 * 
 *         2016-2-1 下午5:01:29
 */
public class VerifyCommonUtil {

	/**
	 * 帐号校验规则
	 */
	private final static Pattern PWD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{8,20}$");

	/**
	 * 密码校验规则
	 */
	private final static Pattern ACCOUNT_PATTERN = Pattern.compile("^\\w{8,20}$");

	/**
	 * 密码通用校验工具
	 * 
	 * @param str
	 *            需要校验的参数
	 * @return true 校验通过 false 校验失败
	 */
	public static boolean isPwdMatch(String str) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		return PWD_PATTERN.matcher(str).matches();
	}

	/**
	 * 帐户名通用校验工具
	 * 
	 * @param str
	 *            需要校验的参数
	 * @return true 校验通过 false 校验失败
	 */
	public static boolean isAccountMatch(String str) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		return ACCOUNT_PATTERN.matcher(str).matches();
	}

}
