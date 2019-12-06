package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex {

    public static void main(String[] args) {

        //1
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d)(.*)";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(3) );
        } else {
            System.out.println("NO MATCH");
        }
        //匹配邮箱
        String email = "clerk.yang1@or12312d-erplus.com";
        String p1 = "^\\s*\\w+(?:\\.{0,1}[\\w]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        // 创建 Pattern 对象
        Pattern r1 = Pattern.compile(p1);
        // 现在创建 matcher 对象
        Matcher m1 = r1.matcher(email);
        if (m1.find( )) {
            System.out.println("Found value: " + m1.group(0) );
        } else {
            System.out.println("NO MATCH");
        }

    }


}
