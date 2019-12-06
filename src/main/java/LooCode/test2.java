package LooCode;

import org.apache.commons.lang3.ArrayUtils;

import java.text.DecimalFormat;

public class test2 {


    public static void main(String[] args) {
        int[] a = {1, 3};
        int[] b = {2, 4, 5};
        int[] c = ArrayUtils.addAll(a, b);
        for (int i = 0; i < c.length; i++) {
            for (int j = 1; j < c.length - 1; j++) {
                if (c[j] > c[j + 1]) {
                    int temp = c[j];
                    c[j] = c[j + 1];
                    c[j + 1] = temp;
                }
            }
        }
        //二分法
        DecimalFormat df = new DecimalFormat("0.0");//设置保留位数
        int d = c.length;
        int e = d / 2;
        if (d % 2 == 0) {
            System.out.println(df.format((float) (c[e - 1] + c[e]) / 2));
        } else {
            System.out.println(df.format((float) (c[e] + c[e + 1]) / 2));
        }
    }

}
