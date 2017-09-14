package com.gistandard.transport.tools.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import com.gistandard.transport.tools.annotation.ObjectFieldNotNull;

public class ObjectFiledNullVerityUtil {

	/**
	 * 递归注解校验对象属性的非空性
	 * 
	 * @param obj
	 *            需要校验的对象
	 * @param clazz
	 *            校验的注解
	 * @param nullFieldMap
	 *            返回的信息
	 * @param index
	 *            针对于集合中的对象下标
	 */
	public static void verifyFieldNotNull(Object obj, Class clazz, Map<String, List<String>> nullFieldMap, Integer index) {

		if (obj == null) {
			addEmptyListInfo(nullFieldMap, "入参", "入参对象");
			return;
		}

		Field[] fieldArr = obj.getClass().getDeclaredFields();
		for (Field field : fieldArr) {
			// 检查该属性是否有指定的注解clazz，并且校验的类型要一致
			if (field.isAnnotationPresent(clazz)) {
				// 反射获取该属性值
				Object fieldObj = Reflections.invokeGetter(obj, field.getName());
				// 属性值为空，将其设置到返回的Map中
				if (fieldObj == null) {
					setEmptyInfo(nullFieldMap, obj, field, index);
				}
				// 如果值是字符串类型的，判断是否为空字符串
				else if (fieldObj instanceof String) {
					String s = (String) fieldObj;
					if (StringUtils.isBlank(s)) {
						setEmptyInfo(nullFieldMap, obj, field, index);
					}
				}
				// 如果属性值是集合，首先判断大小，空集合直接返回空信息，不为空，则对集合中的每个对象循环递归校验非空性
				else if (fieldObj instanceof Collection) {
					if (((Collection) fieldObj).size() <= 0) {
						setEmptyInfo(nullFieldMap, obj, field, index);
					} else {
						Integer i = 0;
						for (Object elementObj : (Collection) fieldObj) {
							verifyFieldNotNull(elementObj, clazz, nullFieldMap, i++);
						}
					}
				}
				// 其他情况，则继续递归判断该对象属性中是否有空
				else {
					verifyFieldNotNull(fieldObj, clazz, nullFieldMap, index);
				}
			}
			// 如果该对象是一个集合，但是不需要校验必填性，但是集合中的元素需要交验元素对象的属性非空性，则循环递归校验
			else if (field.getGenericType().toString().contains("java.util.List")) {
				Object fieldObj = Reflections.invokeGetter(obj, field.getName());
				Collection collection = (Collection) fieldObj;
				if (CollectionUtils.isNotEmpty(collection)) {
					Integer i = 0;
					for (Object elementObj : collection) {
						verifyFieldNotNull(elementObj, clazz, nullFieldMap, i++);
					}
				}
			}
		}
	}

	/**
	 * 设置字段为空信息
	 * 
	 * @param nullFieldMap
	 *            记录信息的map
	 * @param obj
	 *            校验的对象
	 * @param field
	 *            校验的字段
	 * @param index
	 *            集合元素的下标
	 */
	private static void setEmptyInfo(Map<String, List<String>> nullFieldMap, Object obj, Field field, Integer index) {
		String key = index == null ? obj.getClass().getSimpleName() : obj.getClass().getSimpleName() + "_" + index;
		if (nullFieldMap.containsKey(key)) {
			nullFieldMap.get(key).add(field.getName());
		} else {
			addEmptyListInfo(nullFieldMap, key, field.getName());
		}
	}

	private static void addEmptyListInfo(Map<String, List<String>> nullFieldMap, String objName, String fieldName) {
		List<String> list = new ArrayList<String>();
		list.add(fieldName);
		nullFieldMap.put(objName, list);
	}

}
