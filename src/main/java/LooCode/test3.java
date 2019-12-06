package LooCode;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

import static java.util.Comparator.comparingInt;

public class test3 {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        List<Integer> list2 = Lists.newArrayList();
        list2.add(3);
        list2.add(2);
        list2.add(1);
        Collections.sort(list2, comparingInt(o -> list.indexOf(o)));
        list2.forEach(v-> System.out.println(v));
    }

}
