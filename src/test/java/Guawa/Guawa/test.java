package Guawa.Guawa;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.List;
import java.util.Scanner;


public class test {

    static List<Integer> arr2 = Lists.newArrayList();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("输入arr1:");

        String value = sc.next();
        if (value == null) {
            System.out.println("请输入数字");
        }

        String[] list = value.split(",");
        List<Integer> arr1 = Lists.newArrayList();
        //封装arr1
        for (String v : list) {
            try {
                int a = Integer.valueOf(v);
                arr1.add(a);
            } catch (Exception e) {
                System.out.println("输入的" + v + "不是整数");
                break;
            }
        }

        //判断是否是偶数个整数
        if (arr1.size() % 2 != 0) {
            System.out.println("必须输入偶数个整数");
        }

        //首选判断整个数组之和是否为偶数
        int sum = arr1.stream().mapToInt(x -> x).sum();

        if (sum % 2 != 0) {
            System.out.println("输入数值不合适");
            return;
        }
        //循环arr1
        List<Integer> arr2 = Lists.newArrayList();
        List<Integer> arr3 = Lists.newArrayList();
        int half = sum / 2;
        combine(0, arr1.size() / 2, arr1, half);

    }


    /**
     * 组合
     * 按一定的顺序取出元素，就是组合,元素个数[C arr.len 3]
     *
     * @param index 元素位置
     * @param k     选取的元素个数
     * @param arr   数组
     * @param half  平均值
     */
    public static void combine(int index, int k, List<Integer> arr, int half) {
        Ordering<Integer> ordering = Ordering.natural();

        if (k == 1) {
            for (int i = index; i < arr.size(); i++) {
                arr2.add(arr.get(i));
                Integer sum = arr2.stream().mapToInt(x -> x).sum();
                if (sum.equals(half)) {
                    System.out.print("输出arr2:" + ordering.sortedCopy(arr2).toString() + ",");
                    arr.removeAll(arr2);
                    System.out.print("arr3:" + ordering.sortedCopy(arr).toString() + ",");
                    System.out.print("sum = " + half);
                    break;
                }
                Integer value = arr.get(i);
                arr2.remove(value);
            }
        } else if (k > 1) {
            for (int i = index; i <= arr.size() - k; i++) {
                arr2.add(arr.get(i)); //arr2都是临时性存储一下
                combine(i + 1, k - 1, arr, half); //索引右移，内部循环，自然排除已经选择的元素
                Integer value = arr.get(i);
                arr2.remove(value); //arr2因为是临时存储的，上一个组合找出后就该释放空间，存储下一个元素继续拼接组合了
            }
        } else {
            return;
        }
    }
}
