/**
 *Java8 新增了非常多的特性：

 *Lambda 表达式 − Lambda允许把函数作为一个方法的参数（函数作为参数传递进方法中。

 *方法引用 − 方法引用提供了非常有用的语法，可以直接引用已有Java类或对象（实例）的方法或构造器。与lambda联合使用，方法引用可以使语言的构造更紧凑简洁，减少冗余代码。

 *默认方法 − 默认方法就是一个在接口里面有了一个实现的方法。

 *新工具 − 新的编译工具，如：Nashorn引擎 jjs、 类依赖分析器jdeps。

 *Stream API −新添加的Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中。

 *Date Time API − 加强对日期与时间的处理。

 *Optional 类 − Optional 类已经成为 Java 8 类库的一部分，用来解决空指针异常。

 *Nashorn, JavaScript 引擎 − Java 8提供了一个新的Nashorn javascript引擎，它允许我们在JVM上运行特定的javascript应用。

 *更多的新特性可以参阅官网：What's New in JDK 8
 * 
 * 
 * 
 */

package com.geekbo.demo.java8.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author guangbo
 *
 */
public class Java8New {
   public static void main(String args[]){
   
      List<String> list1 = new ArrayList<String>();
      list1.add("Google ");
      list1.add("Facebook ");
      list1.add("Geekbo ");
      list1.add("Twitter ");
      list1.add("Bitfiness ");
      list1.add("Quant ");
        
      List<String> list2 = new ArrayList<String>();
      list2.add("Google ");
      list2.add("Facebook ");
      list2.add("Geekbo ");
      list2.add("Twitter ");
      list2.add("Bitfiness ");
      list2.add("Quant ");
        
      Java8New newer = new Java8New();
      System.out.println("使用 Java 7 语法: ");
        
      newer.sortUsingJava7(list1);
      System.out.println(list1);
      System.out.println("使用 Java 8 语法: ");
        
      newer.sortUsingJava8(list2);
      System.out.println(list2);
   }
   
   // 使用 java 7 排序
   private void sortUsingJava7(List<String> names){   
      Collections.sort(names, new Comparator<String>() {
         @Override
         public int compare(String s1, String s2) {
            return s1.compareTo(s2);
         }
      });
   }
   
   // 使用 java 8 排序
   private void sortUsingJava8(List<String> names){
      Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
   }
}
