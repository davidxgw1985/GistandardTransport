package com.gistandard.transport.tools.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 对象非空校验工具
 * 
 * @author ShengHao
 *
 * @date 2016年1月26日 下午2:46:56
 */
public class NotNullUtil {

	/**
	 * 校验通过
	 */
	public final static String SUCCESS_CODE = "0";

	/**
	 * 校验失败
	 */
	public final static String FAIL_CODE = "1";

	/**
	 * 简单对象参数校验，此方法只能用于简单对象，对象中含有集合的不支持，后面有需求可以拓展
	 */
	public static Map<String, Object> nullVerifyForObject(Object obj, List<String> notNullKeyList) {

		if (obj == null) {
			return buildReturnMap(FAIL_CODE, "所传参数为NULL");
		}

		Map<String, Object> verifyResultMap = buildReturnMap(SUCCESS_CODE, "校验通过");
		try {
			verifyResultMap = nullVerifyForMap(buildVerifyParamMap(obj), notNullKeyList);
		} catch (Exception e) {
			// 这边不会出现问题
			e.printStackTrace();
		}
		return verifyResultMap;
	}

	private static Map<String, Object> buildVerifyParamMap(Object obj) throws Exception {
		Map<String, Object> verifyParamMap = new HashMap<String, Object>();
		Field[] fieldArr = obj.getClass().getDeclaredFields();
		for (Field field : fieldArr) {
			field.setAccessible(true);
			verifyParamMap.put(field.getName(), field.get(obj));
		}
		return verifyParamMap;
	}

	/**
	 * 校验入参的必填字段
	 * 
	 * @param paramMap
	 *            入参
	 * @param notNullKeyList
	 *            非空字段列表
	 * @return code：0-符合要求<br>
	 *         code：1-有未传入的必填字段<br>
	 *         data：类型List，必填未填字段，code=0为空
	 */
	public static Map<String, Object> nullVerifyForMap(Map<String, Object> paramMap, List<String> notNullKeyList) {

		Map<String, Object> verifyResultMap = buildReturnMap(SUCCESS_CODE, "校验通过");
		if (CollectionUtils.isEmpty(notNullKeyList)) {
			return verifyResultMap;
		}

		// 必填未填字段key列表-针对paramMap中有这个key判断
		List<String> nullList1 = new ArrayList<String>();

		// 针对paramMap中有这个key判断
		for (Entry<String, Object> entry : paramMap.entrySet()) {
			if (notNullKeyList.contains(entry.getKey())) {
				if (entry.getValue() == null) {
					nullList1.add(entry.getKey());
				}
				if ((entry.getValue() instanceof String) && StringUtils.isEmpty((String) entry.getValue())) {
					nullList1.add(entry.getKey());
				}
			}
		}

		// 必填未填字段key列表-针对paramMap中没有这个key判断
		List<String> nullList2 = new ArrayList<String>();
		// 针对paramMap没有这个key的判断
		Set<String> paramKeySet = paramMap.keySet();
		for (String notNullkey : notNullKeyList) {
			if (!paramKeySet.contains(notNullkey)) {
				nullList2.add(notNullkey);
			}
		}
		nullList1.addAll(nullList2);

		if (CollectionUtils.isNotEmpty(nullList1)) {
			verifyResultMap = buildReturnMap(FAIL_CODE, nullList1);
		}

		return verifyResultMap;
	}

	private static Map<String, Object> buildReturnMap(String code, Object nullList) {
		Map<String, Object> verifyResultMap = new HashMap<String, Object>();
		verifyResultMap.put(CODE, code);
		verifyResultMap.put(MSG, nullList);
		return verifyResultMap;
	}

	public final static String CODE = "code";
	public final static String MSG = "msg";

}
