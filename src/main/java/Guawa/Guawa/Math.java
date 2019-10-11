package Guawa.Guawa;

import com.google.common.math.IntMath;

import java.math.RoundingMode;

/**
 * guawa中的math主要是针对，int long double bigInteger做处理的
 */
public class Math {

    //int long double bigInteger三个方法类似 处理的数不一样，方法都相同
    public static void testMath() {
        //返回两个值之和
        try {
            System.out.println(IntMath.checkedAdd(Integer.MAX_VALUE, Integer.MAX_VALUE));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }

        //返回p除与q，使用指定RoundingMode的四舍五入结果。
        System.out.println(IntMath.divide(100, 5, RoundingMode.UNNECESSARY));

        try {
            //无尽除
            System.out.println(IntMath.divide(100, 3, RoundingMode.UNNECESSARY));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
        //对数是求指数的运算,比如log2x的意思就是求x是2的多少次幂.
        System.out.println("Log2(2): " + IntMath.log2(4, RoundingMode.HALF_EVEN));
        //对数是求指数的运算,比如log10x的意思就是求x是10的多少次幂.
        System.out.println("Log10(10): " + IntMath.log10(10, RoundingMode.HALF_EVEN));
        // sqrt平方根 pow 10的2次幂
        System.out.println("sqrt(100): " + IntMath.sqrt(IntMath.pow(10, 2), RoundingMode.HALF_EVEN));
        //返回a, b的最大公约数
        System.out.println("gcd(100,50): " + IntMath.gcd(100, 50));
        //返回x模m，一个非负的值小于m以下。
        System.out.println("modulus(100,50): " + IntMath.mod(100, 50));
        //返回n个！，也就是说，前n个正整数的乘积，如果n==0则返回1，或者是Integer.MAX_VALUE如果结果不适合在一个int值。
        System.out.println("factorial(5): " + IntMath.factorial(5));

    }


    public static void main(String args[]) {
        testMath();
    }


}
