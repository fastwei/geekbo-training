/**
 * 
 */
package com.geekbo.demo.java8.java8;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 为了在运行时获得Java程序中方法的参数名称，老一辈的Java程序员必须使用不同方法，例如Paranamer liberary。Java
 * 8终于将这个特性规范化，在语言层面（使用反射API和Parameter.getName()方法）和字节码层面（使用新的javac编译器以及-parameters参数）提供支持。
 * 
 * 在Java 8中这个特性是默认关闭的，因此如果不带-parameters参数编译上述代码并运行Parameter: arg0
 * 
 * @author guangbo
 *
 */
public class ParameterNames {
	public static void main(String[] args) throws Exception {
		Method method = ParameterNames.class.getMethod("main", String[].class);
		for (final Parameter parameter : method.getParameters()) {
			System.out.println("Parameter: " + parameter.getName());
		}
	}
}
