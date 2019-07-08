package Guawa.Guawa;

import com.google.common.base.Functions;
import com.google.common.collect.Maps;
import com.sun.javafx.collections.MappingChange;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

/**
 * 函数式编程
 */
public class Functional {

    /**
     * 函数式编程的条件：
     * 代码减少
     * 确保有高的效率
     */

    public static void functionProgram() {

        //Function接口：这说明在java编程当中可以引入函数式编程。同时也说明了如何使用Function接口以及最好的使用方式。
        //Functions类：Functions类包含一些实用的方法来操作Fucntion接口的实例。

        Function<Date, String> function = new Function<Date, String>() {
            @Override
            public String apply(Date date) {
                return new SimpleDateFormat("dd/mm/yyyy").format(date);
            }
        };
        System.out.println(function.apply(new Date()));

        //使用场景，比如我们需要在一个州的缩写中找到州的Object信息，使用以下就可以找到。但是apply不允许可以为空，可以使用state方法代替，返回key的默认值
        HashMap<String, Object> maps = Maps.newHashMap();
        Function<String, Object> lookup = (Function<String, Object>) Functions.forMap(maps);
        lookup.apply("NY");

        //Predicate接口：这个接口是评估一个对象是否满足一定条件，如果满足则返回true。
        //Predicates类：这个类是对于Predicate接口的指南类，它实现了Predicate接口并且非常实用的静态方法。


        //Supplier接口：这个接口可以提供一个对象通过给定的类型。我们也可以看到通过各种各样的方式来创建对象。
        //Suppliers类：这个类是Suppliers接口的默认实现类。

    }


    public static void main(String[] args) {
        functionProgram();
    }

}
