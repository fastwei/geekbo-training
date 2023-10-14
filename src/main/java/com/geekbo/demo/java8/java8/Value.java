/**
 * 
 */
package com.geekbo.demo.java8.java8;

/**
 * @author guangbo
 *
 */
public class Value<T> {
	public static <T> T defaultValue() {
		return null;
	}

	public T getOrDefault(T value, T defaultValue) {
		return (value != null) ? value : defaultValue;
	}
}
