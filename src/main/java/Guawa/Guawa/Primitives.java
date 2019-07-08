package Guawa.Guawa;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import java.util.List;

/**
 * 原生基本类型封装
 */
public class Primitives {


    /**
     * 主要是对八种数据类型的封装
     *
     * @param args
     */

    public static void main(String[] args) {

        //List<Wrapper> asList(prim… backingArray) 把数组转为相应包装类的List

        List<Integer> list = Ints.asList(1, 2, 4, 5);
        System.out.println(list);

        //prim[] toArray (Collection < Wrapper > collection) 把集合拷贝为数组，和collection.toArray() 一样线程安全

        List<Integer> ints = Lists.newArrayList();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        int a[] = Ints.toArray(list);
        System.out.println(a);

        //prim[] concat (prim[]…arrays)  串联多个原生类型数组

        int b[] = new int[]{1, 3, 4, 5};
        int c[] = new int[]{1, 2, 3, 4, 5};
        int d[] = Ints.concat(b, c);
        System.out.println(d);

        // boolean contains (prim[]array, prim target) 判断原生类型数组是否包含给定值
        int e[] = new int[]{1, 2, 3, 4, 5, 1};
        System.out.println(Ints.contains(e, 1));

        //int indexOf (prim[]array, prim target) 给定值在数组中首次出现处的索引，若不包含此值返回 - 1

        System.out.println(Ints.indexOf(e, 1));

        //int lastIndexOf (prim[]array, prim target) 给定值在数组最后出现的索引，若不包含此值返回 - 1

        System.out.println(Ints.lastIndexOf(e, 1));

        //prim min (prim…array) 数组中最小的值

        System.out.println(Ints.min(e));

        //prim max (prim…array) 数组中最大的值
        System.out.println(Ints.max(e));

        //String join (String separator, prim…array) 把数组用给定分隔符连接为字符串
        System.out.println(Ints.join(";", e));


    }

}
