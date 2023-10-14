/**
 * 
 */
package com.geekbo.demo.java8.java8;

/**
 * @author guangbo
 * 
 */
public class Lambda2 {
	//lambda 表达式只能引用标记了 final 的外层局部变量，这就是说不能在 lambda 内部修改定义在域外的局部变量，否则会编译错误。
	final static String salutation = "Hello! ";
	   
	   public static void main(String args[]){
	      GreetingService greetService1 = message -> 
	      System.out.println(salutation + message);
	      greetService1.sayMessage("Geekbo");
	    
	   
	      //我们也可以直接在 lambda 表达式中访问外层的局部变量：
		   final int num = 1;
	       Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
	       s.convert(2);  // 输出结果为 3
	   }

	   
	   interface GreetingService {
		      void sayMessage(String message);
		   }
	   
	   
	   interface Converter<T1, T2> {
	       void convert(int i);
	   }

}
