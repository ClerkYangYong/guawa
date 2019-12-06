package LooCode;

import com.google.common.collect.Lists;

import java.util.List;

public class test5 {

    //Z型树排列
    public static void main(String[] args) {

        String s = "abcdefghijk";
        int num = 3;

        List<StringBuilder> lists = Lists.newArrayList();
        for (int i = 0; i < num; i++) {
            lists.add(new StringBuilder());
        }
        int a = 0;
        int flag = -1;

        for (char c : s.toCharArray()) {
            lists.get(a).append(c);
            if (a == 0 || a == num - 1) {
                flag = -flag;
            }
            a += flag;
        }
        lists.forEach(v -> System.out.println(v));

    }


}
