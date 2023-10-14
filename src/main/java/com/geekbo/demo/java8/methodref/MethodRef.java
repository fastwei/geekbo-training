/**
 * 
 */
package com.geekbo.demo.java8.methodref;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guangbo
 *
 */
public class MethodRef {
	public static void main(String args[]){
	      List<String> names = new ArrayList<String>();
	        
	      names.add("Google");
	      names.add("Geekbo");
	      names.add("Quant");
	      names.add("Bitfiness");
	        
	      names.forEach(System.out::println);
	   }
}
