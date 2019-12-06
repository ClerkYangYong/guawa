package LooCode;

public class test6 {

    public static void main(String[] args) {
        int x = 123;
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                rev = 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                rev = 0;
            }
            rev = rev * 10 + pop;
        }

        System.out.println(rev);
    }

}
