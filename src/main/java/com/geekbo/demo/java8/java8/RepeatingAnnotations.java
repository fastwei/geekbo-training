/**
 * 
 */
package com.geekbo.demo.java8.java8;

import java.lang.annotation.*;

/**
 * 自从Java
 * 5中引入注解以来，这个特性开始变得非常流行，并在各个框架和项目中被广泛使用。不过，注解有一个很大的限制是：在同一个地方不能多次使用同一个注解。Java
 * 8打破了这个限制，引入了重复注解的概念，允许在同一个地方多次使用同一个注解。 在Java
 * 8中使用@Repeatable注解定义重复注解，实际上，这并不是语言层面的改进，而是编译器做的一个trick，底层的技术仍然相同。
 * 
 * 
 * @author guangbo
 *
 */
public class RepeatingAnnotations {
	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Filters {
		Filter[] value();
	}

	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@Repeatable(Filters.class)
	public @interface Filter {
		String value();
	};

	@Filter("filter1")
	@Filter("filter2")
	public interface Filterable {
	}

	public static void main(String[] args) {
		// 反射API提供了一个新的方法：getAnnotationsByType()，可以返回某个类型的重复注解，例如Filterable.class.getAnnoation(Filters.class)将返回两个Filter实例
		for (Filter filter : Filterable.class.getAnnotationsByType(Filter.class)) {
			System.out.println(filter.value());
		}
	}
}
