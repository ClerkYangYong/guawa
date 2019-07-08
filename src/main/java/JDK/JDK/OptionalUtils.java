package JDK.JDK;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

/**
 * Created by order
 * 2019/3/26 20:32
 */
public class OptionalUtils {

    public static void main(String[] args) {
        // 1. empty 方法
        Optional<String> optional = Optional.empty();
        // 抛出NoSuchElementException
//        System.out.println(optional.get());

        // 2. of 方法 & get方法
        optional = Optional.of("七夜雪");
        System.out.println("2 ---> " + optional.get());

        // 3. isPresent方法
        if (optional.isPresent()) {
            System.out.println("3 ---> " + optional.get());
        }

        // 4. ifPresent, 测试案例3中这种判断方式和现有的先判断是否为null, 再处理是一致的, 更好的一种方式是使用ifPresent
        optional.ifPresent(item -> System.out.println("4 ---> " + item));
        optional = Optional.empty();
        // 这一行因为optional为空, 所以不会执行输出
        optional.ifPresent(item -> System.out.println("4 ---> " + item));

        // 5. filter方法
        optional = Optional.of("七夜雪").filter(item -> "七夜".equals(item));
        System.out.println("5 --->" + optional.isPresent());
        optional = Optional.of("七夜雪").filter(item -> "七夜雪".equals(item));
        System.out.println("5 --->" + optional.isPresent() + ", value : " + optional.get());

        // 6. map 方法
        Optional<Integer> optional1 = Optional.of(1);
        optional = optional1.map(item -> "七夜雪-> " + item);
        System.out.println("6 ---> " + optional.get());
        optional = optional1.map(item -> null);
        System.out.println("6 ---> " + optional.isPresent());
        optional1 = Optional.empty();
        optional = optional1.map(item -> "七夜雪-> " + item);
        System.out.println("6 ---> " + optional.isPresent());

        // 7. flatMap 方法
        optional1 = Optional.of(1);
        optional = optional1.flatMap(item -> Optional.of("七夜雪-> " + item));
        System.out.println("7 ---> " + optional.get());

        // 8. orElse方法
        System.out.println("8 --->" + optional.get());
        optional = Optional.empty();
        System.out.println("8 --->" + optional.orElse("碧落"));

        // 9. orElseGet 方法
        System.out.println("9 --->" + optional.orElseGet(() -> "红尘"));
        optional = Optional.of("七夜雪");
        System.out.println("9 --->" + optional.orElseGet(() -> "红尘"));

        // 10. ofNullable 方法
        optional = Optional.ofNullable(null);
        System.out.println("10 --->" + optional.isPresent());
        optional = Optional.ofNullable("七夜雪");
        System.out.println("10 --->" + optional.isPresent());


        System.out.println(Optional.ofNullable(null).orElse("错误"));

        List<String> list = null;
        Optional.ofNullable(list).ifPresent(a -> System.out.println("null"));
    }
}
