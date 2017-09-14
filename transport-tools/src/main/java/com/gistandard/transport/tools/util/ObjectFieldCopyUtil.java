package com.gistandard.transport.tools.util;

import java.lang.reflect.Field;
import java.util.Map;

public class ObjectFieldCopyUtil {

	/**
	 * 赋值属性
	 * 
	 * @param propertiesMap
	 *            属性映射列表，key代表赋值源的属性名，value代表被赋值的属性名
	 * @param obj
	 *            被赋值的对象
	 * @param copyObj
	 *            赋值源对象
	 * @throws Exception
	 *             异常
	 */
	public static void copyProperties(Map<String, String> propertiesMap, Object obj, Object copyObj) throws Exception {
		// 待复制的属性列表
		Field[] fieldArr = copyObj.getClass().getDeclaredFields();
		for (Field field : fieldArr) {
			String fieldName = field.getName();
			if (propertiesMap.containsKey(fieldName)) {
				// 反射获取该属性值
				Object fieldValue = Reflections.invokeGetter(copyObj, field.getName());
				Reflections.invokeSetter(obj, propertiesMap.get(fieldName), fieldValue);
			}
		}
	}

}
