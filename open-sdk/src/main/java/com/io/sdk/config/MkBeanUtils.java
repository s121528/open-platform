package com.io.sdk.config;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 扩展Spring的BeanUtils
 * @author 马凯
 * @date 2016-10-13 17:57:48
 */
public class MkBeanUtils extends BeanUtils {
	/**
	 * 把源对象的某几个属性复制到目标对象中
	 * @param source 源对象
	 * @param target 目标对象
	 * @param properties 属性名
	 * @throws BeansException
	 */
	public static void copySomeProperties(Object source, Object target, String... properties) throws BeansException {

		Assert.notNull(source, "被复制的对象不能为空");
		Assert.notNull(target, "目标对象不能为空");

		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (properties != null ? Arrays.asList(properties) : null);
		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreList == null || ignoreList.size() == 0 || ignoreList.contains(targetPd.getName()))) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = readMethod.invoke(source);
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, value);
						} catch (Throwable ex) {
							throw new FatalBeanException("不能复制属性 '" + targetPd.getName() + "' 到目标对象", ex);
						}
					}
				}
			}
		}
	}

	public static void copyNotNullProperties(Object source, Object target, String... properties) throws BeansException {
		Assert.notNull(source, "被复制的对象不能为空");
		Assert.notNull(target, "目标对象不能为空");

		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (properties != null ? Arrays.asList(properties) : null);
		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreList == null || ignoreList.size() == 0 || ignoreList.contains(targetPd.getName()))) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = readMethod.invoke(source);
							//只拷贝不为null的属性
							if (value != null) {
								if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
									writeMethod.setAccessible(true);
								}
								writeMethod.invoke(target, value);
							}
						} catch (Throwable ex) {
							throw new FatalBeanException("不能复制属性 '" + targetPd.getName() + "' 到目标对象", ex);
						}
					}
				}
			}
		}
	}

	public static <T> List<T> copyList(List sourceList, Class<T> clazz) {
		List<T> listResult = new ArrayList<>();
		try {
			for (Object o : sourceList) {
				T t = clazz.newInstance();
				BeanUtils.copyProperties(o, t);
				listResult.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}
}
