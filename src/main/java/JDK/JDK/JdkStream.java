package JDK.JDK;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by order
 * 2019/3/27 10:33
 */
public class JdkStream {


    public static void main(String[] args) {

        //通过集合Collection获取
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        Stream<Integer> stream = list.stream();

        //通过数组获取
        String[] array = {"are", "you", "ok"};
        Stream<String> stream1 = Arrays.stream(array);

        //直接通过值获取
        Stream<String> stream3 = Stream.of("are", "you", "ok");


        //使用filter做筛选
        List<String> list1 = Arrays.asList("are", "you", "", "ok");
        List<String> filted = list1.stream()
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toList());
        System.out.println(filted);

        //使用distinct去重操作
        List<String> list2 = Arrays.asList("are", "you", "you", "ok");
        List<String> distincted = list2.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distincted);

        //截取流的前N个元素
        List<String> list3 = Arrays.asList("are", "you", "ok", "ok");
        List<String> limit = list3.stream()
                .limit(3).collect(Collectors.toList());
        System.out.println(limit);

        //跳过流的前n个元素
        List<String> list4 = Arrays.asList("are", "you", "ok", "ok");
        List<String> skip = list4.stream()
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(skip);

        //对流中的每个元素执行一个函数，使得元素转换成另一种类型输出。流会将每一个元素输送给map函数，
        // 并执行map中的Lambda表达式，最后将执行结果存入一个新的流中。
        //如：将 list 中每一个 Integer类型元素自增后转化为 String类型
        List<Integer> list5 = Arrays.asList(1, 2, 3, 4, 5);
        List<String> result = list5.stream()
                .map(x -> String.valueOf(++x))
                .collect(Collectors.toList());
        //统计集合中>3的元素数量
        int result1 = (int) list5.stream().filter(v -> v > 3).count();
        System.out.println(result);
        System.out.println(result1);

        //合并多个流 flatMap
        List<String> list6 = new ArrayList<String>();
        list6.add("I am a boy");
        list6.add("I love the girl");
        list6.add("But the girl loves another girl");
        list6.stream().map(line -> line.split(" "))    //将每一个项分词，并映射为数组
                .flatMap(Arrays::stream)       //将每一个分项数组组合并到主流中，形成一个包含所有分项数组的总数组流
                .distinct()                   //去重
                .forEach(System.out::println);   //打印

        //匹配元素
        //是否匹配任一元素：anyMatch
        //判断流中是否含有>10的项
        List<Integer> list7 = Arrays.asList(1, 2, 3, 4, 5, 6);
        boolean result2 = list7.stream()
                .anyMatch(x -> x > 10);
        System.out.println(result2);

        // 是否匹配所有元素：allMatch
        List<Integer> list8 = Arrays.asList(1, 2, 3, 4, 5, 6);
        boolean result3 = list8.stream()
                .allMatch(x -> x > 5);
        System.out.println(result3);

        // 是否未匹配所有元素：noneMatch
        //判断流中是否 全部不满足 >5
        List<Integer> list9 = Arrays.asList(1, 2, 3, 4, 5, 6);
        boolean result4 = list9.stream()
                .noneMatch(x -> x > 5);
        System.out.println(result4);

        //获取任一元素 ：findAny
        List<Integer> list10 = Arrays.asList(1, 2, 3, 4, 5, 6);
        Optional<Integer> result5 = list10.stream().findAny();
        if (result5.isPresent()) {
            Integer randValue = result5.get();
            System.out.println(randValue);
        }
        //合并的调用方式
        Integer randValue = list10.stream().findAny().orElse(0);
        System.out.println(randValue);

        // 获取第一个元素：findFirst
        Optional<Integer> result6 = list10.stream().findFirst();
        if (result6.isPresent()) {
            Integer randValue1 = result6.get();
            System.out.println(randValue1);
        }

        //获取一个整型列表的最大值，最小值
        Random random = new Random();
        List<Integer> list11 = random.ints(1, 100).limit(50).boxed().collect(Collectors.toCollection(ArrayList::new));

        System.out.println(list11);

        int max = list11.stream().max(Integer::compare).orElse(-1);   //获取最大值
        System.out.println(max);

        int min = list11.stream().min(Integer::compare).orElse(-1);   //获取最小值
        System.out.println(min);

        //使用基于数据流的方式，将流装载相应的 SummaryStatistics 来进行归约计算，可以实现更多的操作；
        IntSummaryStatistics stats = list11.stream().mapToInt(x -> x).summaryStatistics();
        int max1 = stats.getMax();        //获取最大值
        System.out.println(max1);
        int min1 = stats.getMin();        //获取最小值
        System.out.println(min1);
        double sum = stats.getSum();    //获取总值
        System.out.println(sum);
        double avg = stats.getAverage();  //获取平均值
        System.out.println(avg);
        long count = stats.getCount();     //获取总数量
        System.out.println(count);

        //遍历流 forEach
        Random random1 = new Random();
        random1.ints().limit(2).forEach(System.out::println);

        //排序 sorted 使用guawa的Ordering类进行排序
        Random random2 = new Random();
        random2.ints().limit(2).sorted().forEach(System.out::println);   //升序

        //1）将 Stream 转换成数值流
        List<Double> list12 = Arrays.asList(2.3, 2.4, 2.5, 2.7, 2.8);

        //普通Stream转为数值Stream
        DoubleStream doubles = list12.stream().mapToDouble(x -> x);    //转化为DoubleStream
        doubles.forEach(System.out::println);
        IntStream ints = list12.stream().mapToInt(x -> Integer.parseInt(String.format("%.0f", x))); //转化为IntStream，同时进行取舍操作
        ints.forEach(System.out::println);



    }


}
