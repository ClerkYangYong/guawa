package LooCode;

/**
 * 贪心算法
 */
public class Test8 {

    public static void main(String[] args) {
        int leftShift = 5;
        System.out.println("十进制:" + leftShift + ", 二进制:" + Integer.toBinaryString(leftShift));
        int newLeftShift = leftShift >> 1;
        System.out.println("左移2位后十进制:" + newLeftShift + ", 左移2位后二进制" + Integer.toBinaryString(newLeftShift));
    }

}
