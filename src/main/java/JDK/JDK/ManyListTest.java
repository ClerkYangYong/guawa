package JDK.JDK;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by order
 * 2019/4/2 16:10
 */
public class ManyListTest {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        List<String> list2 = new ArrayList<>();
        list2.add("3");
        list2.add("4");
        list2.add("5");
        list2.add("6");
        list2.add("7");
        list2.add("8");
        list2.add("9");
        list2.add("10");
        List<String> list3 = new ArrayList<>();
        list3.add("7");
        list3.add("8");
        List<List<String>> allList = new ArrayList<>();
        allList.add(list1);
        allList.add(list2);
        allList.add(list3);
        List<String> result = calculateCombination(allList);
        result.forEach(v->
                System.out.println(v));
    }

    public static List<String> calculateCombination(List<List<String>> inputList) {
        List<String> result = Lists.newArrayList();
        List<Integer> combination = new ArrayList<Integer>();
        int n = inputList.size();
        for (int i = 0; i < n; i++) {
            combination.add(0);
        }
        int i = 0;
        boolean isContinue = false;
        do {
            //打印一次循环生成的组合
            List<String> list = Lists.newArrayList();
            for (int j = 0; j < n; j++) {
                list.add(inputList.get(j).get(combination.get(j)));
            }
            result.add(StringUtils.join(list, "|"));

            i++;
            combination.set(n - 1, i);
            for (int j = n - 1; j >= 0; j--) {
                if (combination.get(j) >= inputList.get(j).size()) {
                    combination.set(j, 0);
                    i = 0;
                    if (j - 1 >= 0) {
                        combination.set(j - 1, combination.get(j - 1) + 1);
                    }
                }
            }
            isContinue = false;
            for (Integer integer : combination) {
                if (integer != 0) {
                    isContinue = true;
                }
            }
        } while (isContinue);

        return result;
    }
}
