package Guawa.Guawa;

import com.google.common.base.*;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 字符串处理
 */
public class StringsDeal {

    /**
     * ；链接字
     */
    public static void stringJoier() {
        /**
         *joiner实例总是不可变的。用来定义joiner目标语义的配置方法总会返回一个新的joiner实例。这使得joiner实例都是线程安全的，你可以将其定义为static final常量
         */
        Joiner joiner = Joiner.on("; ").skipNulls();
        System.out.println(joiner.join("Harry", null, "Ron", "Hermione"));
    }

    /**
     * 拆分器
     */
    public static void stringPlitter() {

        //jdk中的拆分器,会默认去掉最后面的空字符串
        String[] s = ",a,,b,".split(",");
        System.out.println(s.length);
        Arrays.stream(s).forEach(v -> System.out.print(v));

        //guawa中的拆分

        /**
         *omitEmptyStrings()  从结果中自动忽略空字符串
         *trimResults() 移除结果字符串的前导空白和尾部空白
         *trimResults(CharMatcher) 给定匹配器，移除结果字符串的前导匹配字符和尾部匹配字符
         *limit(int) 	限制拆分出的字符串数量
         *
         * splitter实例总是不可变的。用来定义splitter目标语义的配置方法总会返回一个新的splitter实例。这使得splitter实例都是线程安全的，你可以将其定义为static final常量
         */
        Iterable<String> list = Splitter.on('|').split("asdfas||");
        list.forEach(v -> {
            System.out.println(v);
        });

    }

    /**
     * 字符串匹配器
     */

    public static void stringCharMatcher() {


        //https://www.cnblogs.com/zemliu/p/3345087.html
        String string = " 123123 werdsf  ETE sdfjkl上的看法卡死了 ";

        System.out.println(" 123wer ".trim());


        System.out.println(CharMatcher.WHITESPACE.removeFrom(string));

        //移除control字符
        String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(string);
        System.out.println(noControl);

        //只保留数字字符
        String theDigits = CharMatcher.DIGIT.retainFrom(string);
        System.out.println(theDigits);

        //去除两端的空格，并把中间的连续空格替换成单个空格
        String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(string, ' ');
        System.out.println(spaced);

        //用*号替换所有数字
        String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(string, "*");
        System.out.println(noDigits);

        String s1 = "L1a Aguada - Costa Azul";

        String s2 = "Abu S2amr1a, Al Ain";

        // 只保留数字和小写字母
        String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(string);

        System.out.println("s错误" + CharMatcher.JAVA_DIGIT.retainFrom(s1));


        System.out.println(lowerAndDigit);

        System.out.println(CharMatcher.JAVA_DIGIT.retainFrom("0123123"));

        String s = "Apricot chiffon long, 105 cm, with";
        s = CharMatcher.WHITESPACE.trimAndCollapseFrom(s, ' ').replace(",", "").toLowerCase();
        System.out.println(CharMatcher.WHITESPACE.trimAndCollapseFrom(s, '_'));

        System.out.println(CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(s));


    }

    /**
     * 字符集
     */
    public static void stringCharsets() {
        Charset charset = Charsets.UTF_8;
    }

    /**
     * 大小写格式
     */
    public static void stringCaseFormat() {

        //CaseFormat
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME"));

    }

    public static void main(String[] args) {
        //stringJoier();
        //stringPlitter();
        stringCharMatcher();
        //stringCaseFormat();


    }

}
