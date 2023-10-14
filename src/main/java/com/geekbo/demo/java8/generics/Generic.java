/**
 * 
 */
package com.geekbo.demo.java8.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guangbo
 *
 */
public class Generic {
	public static void main(String[] args) {
		List<String> name = new ArrayList<String>();
		List<Integer> age = new ArrayList<Integer>();
		List<Number> number = new ArrayList<Number>();

		name.add("icon");
		age.add(18);
		number.add(314);

		getData(name);
		getData(age);
		getData(number);

		//getUperNumber(name);//1
		getUperNumber(age);// 2
		getUperNumber(number);// 3

	}

	// 因为getData()方法的参数是List类型的，所以name，age，number都可以作为这个方法的实参，这就是通配符的作用
	public static void getData(List<?> data) {
		System.out.println("data :" + data.get(0));
	}

	// 类型通配符上限通过形如List来定义，如此定义就是通配符泛型值接受Number及其下层子类类型。
	public static void getUperNumber(List<? extends Number> data) {
		System.out.println("data :" + data.get(0));
	}
}
