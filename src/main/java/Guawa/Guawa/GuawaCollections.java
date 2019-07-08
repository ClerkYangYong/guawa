package Guawa.Guawa;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.*;

import java.util.*;

public class GuawaCollections {

    /**
     * 当对象被不可信的库调用时，不可变形式是安全的；
     * 不可变对象被多个线程调用时，不存在竞态条件问题
     * 不可变集合不需要考虑变化，因此可以节省时间和空间。所有不可变的集合都比它们的可变形式有更好的内存利用率（分析和测试细节）；
     * 不可变对象因为有固定不变，可以作为常量来安全使用
     */
    //使用场景：当前集合是一个常量，经常使用时。前提：不能包含null值
    public static void immutableCollection() {

        //jdk中设置不可变集合
        /*List<String> list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");

        System.out.println(list);

        List<String> unmodifiableList = Collections.unmodifiableList(list);

        System.out.println(unmodifiableList);

        List<String> unmodifiableList1 = Collections.unmodifiableList(Arrays.asList("a", "b", "c"));
        System.out.println(unmodifiableList1);

        String temp = unmodifiableList.get(1);
        System.out.println("unmodifiableList [0]：" + temp);

        list.add("baby");
        System.out.println("list add a item after list:" + list);
        System.out.println("list add a item after unmodifiableList:" + unmodifiableList);

        unmodifiableList1.add("bb");
        System.out.println("unmodifiableList add a item after list:" + unmodifiableList1);

        unmodifiableList.add("cc");
        System.out.println("unmodifiableList add a item after list:" + unmodifiableList);*/

        //如果对list做了修改，不可变集合就会变成可变，在多线程中会存在一定的问题

        //guawa中做不可变集合
        //一个immutable集合可以有以下几种方式来创建：
        //(1)用copyOf方法, 譬如, ImmutableSet.copyOf(set)
        //(2)使用of方法，譬如，ImmutableSet.of("a", "b", "c")或者ImmutableMap.of("a", 1, "b", 2)
        //(3)使用Builder类

        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println("list：" + list);

        ImmutableList<String> imlist = ImmutableList.copyOf(list);
        System.out.println("imlist：" + imlist);
        list.add("D");
        System.out.println("imlist：" + imlist);

        ImmutableList<String> imOflist = ImmutableList.of("peida", "jerry", "harry");
        System.out.println("imOflist：" + imOflist);

        ImmutableSortedSet<String> imSortList = ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
        System.out.println("imSortList：" + imSortList);

        list.add("baby");
        System.out.println("list add a item after list:" + list);
        System.out.println("list add a item after imlist:" + imlist);

        ImmutableSet<String> imColorSet =
                ImmutableSet.<String>builder()
                        .add("a")
                        .add("b")
                        .build();
        System.out.println("imColorSet:" + imColorSet);

        //做到集合真正的不可变


    }

    /**
     * 新增的集合类
     */
    public static void guawaNewCollection() {
        //Multiset
        //它可以多次添加相等的元素,继承Collection接口，所以还是一个集合。
        //一般的Set会对相同元素去重，而Multiset则会记下某个元素重复出现的次数。
        //可以理解为Multiset内部维护了一个HashMap，对每个元素的重复次数进行计数，每次插入或者删除元素，都会更新这个HashMap
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("d");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("b");
        multiset.add("b");
        multiset.add("b");

        System.out.println("1.初始值" + multiset);

        System.out.println("2.Total Size : " + multiset.size());

        System.out.println("3.Occurrence of  b: " + multiset.count("b"));

        Set<String> set = multiset.elementSet();

        System.out.println("4.set" + set);

        Iterator<String> iterator = multiset.iterator();
        System.out.print("5.MultiSet [");
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        System.out.println("]");

        System.out.println("6.MultiSet [");
        for (Multiset.Entry<String> entry : multiset.entrySet()) {
            System.out.println("Element: " + entry.getElement() + ", Occurrence(s): " + entry.getCount());
        }
        System.out.println("]");

        multiset.remove("b", 2);

        System.out.println("7.Occurence of 'b' : " + multiset.count("b"));

        //Bimap
        //新的集合类型，它提供了key和value的双向关联的数据结构,有一个前提，保证value唯一的,支持null值。如果key重复会覆盖
        //如果非要插入重复的value，使用forcePut。但是key会覆盖
        BiMap<Integer, String> logfileMap = HashBiMap.create();
        logfileMap.put(1, "a.log");
        logfileMap.put(2, "b.log");
        logfileMap.put(3, "c.log");
        logfileMap.forcePut(4, "a.log");

        System.out.println("8.logfileMap:" + logfileMap);

        //inverse方法会返回一个反转的BiMap，但是注意这个反转的map不是新的map对象，它实现了一种视图关联，这样你对于反转后的map的所有操作都会影响原先的map对象。
        BiMap<String, Integer> filelogMap = logfileMap.inverse();

        System.out.println("9.filelogMap:" + filelogMap);

        filelogMap.remove("a.log");
        System.out.println("10.filelogMap:" + filelogMap);
        System.out.println("11.logfileMap:" + logfileMap);


        //Multimaps一建多值的map
        Multimap<String, String> myMultimap = ArrayListMultimap.create();

        // 添加键值对
        myMultimap.put("Fruits", "Bannana");
        myMultimap.put("Fruits", "Apple");
        myMultimap.put("Fruits", "Pear");
        myMultimap.put("Vegetables", "Carrot");

        // 获得multimap的size
        int size = myMultimap.size();
        System.out.println("12." + size);

        // 获得Fruits对应的所有的值
        Collection<String> fruits = myMultimap.get("Fruits");
        System.out.println("13." + fruits);

        Collection<String> vegetables = myMultimap.get("Vegetables");
        System.out.println("14." + vegetables);

        //遍历Mutlimap
        for (String value : myMultimap.values()) {
            System.out.println("15." + value);
        }

        // Removing a single value
        myMultimap.remove("Fruits", "Pear");
        System.out.println("16." + myMultimap.get("Fruits"));

        // Remove all values for a key
        myMultimap.removeAll("Fruits");
        System.out.println("17." + myMultimap.get("Fruits"));

    }

    /**
     * 新的集合工具类
     */
    public static void newCollectionsUtils() {

        // Collections2，guawa新的集合类
        //guawa新的定义集合方法

        //指定容器的大小 list
        //固定100
        List<String> exactly100 = Lists.newArrayListWithCapacity(100);
        //大约100
        List<String> approx100 = Lists.newArrayListWithExpectedSize(100);

        List<String> list = Lists.newArrayList("1", "2", "3", "3", "4");
        //把list按照指定大小进行分割
        System.out.println("1." + Lists.partition(list, 2));
        //把list进行反转
        System.out.println("2." + Lists.reverse(list));

        //map
        Map<String, String> map = Maps.newHashMap();

        //set
        Set<String> set = Sets.newHashSet("123");

        //Iterables容器
        List<String> list1 = Lists.newArrayList("Apple", "Pear", "Peach", "Banana");
        Predicate<String> condition = new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return ((String) input).startsWith("P");
            }
        };
        boolean allIsStartsWithP = Iterators.all(list1.iterator(), condition);
        System.out.println("3.all result == " + allIsStartsWithP);

        //FluentIterable
        //这里有一个潜在的坑，在高版本(21.0++)的guava中Predicate接口继承了java 8中的java.util.function.Predicate
        //过滤
        FluentIterable<String> startPElements = FluentIterable.from(list1).filter(
                new Predicate<String>() {
                    @Override
                    public boolean apply(String input) {
                        return input.startsWith("P");
                    }
                }
        );
        System.out.println("4." + startPElements.toString());
        //转换,对对象的操作比较明显，例如在一个一个用户对象列表中找出年龄大于20小于30岁的人就比较好用
        ImmutableList<String> transform = FluentIterable.from(list1).transform(
                new Function<String, String>() {
                    @Override
                    public String apply(String input) {
                        return input.toUpperCase();
                    }
                }
        ).toList();
        System.out.println("5." + transform);

        //Table，二维表结构：Table。使用Table可以实现二维矩阵的数据结构
        //示例1
        Table<Integer, Integer, String> tb = HashBasedTable.create();

        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 5; column++) {
                tb.put(row, column, "value of cell (" + row + "," + column + ")");
            }
        }
        for (int row = 0; row < tb.rowMap().size(); row++) {
            Map<Integer, String> rowData = tb.row(row);
            for (int column = 0; column < rowData.size(); column++) {
                System.out.println("6.cell(" + row + "," + column + ") value is:" + rowData.get(column));
            }
        }
        //示例2
        HashBasedTable<Integer, Integer, String> table = HashBasedTable.create();
        table.put(1, 1, "11");
        table.put(1, 2, "12");
        table.put(1, 3, "13");
        table.put(2, 1, "21");
        table.put(2, 2, "22");
        table.put(2, 3, "23");

        boolean contains11 = table.contains(1, 1);
        boolean containColumn2 = table.containsColumn(2);
        boolean containsRow1 = table.containsRow(1);
        boolean containsValue11 = table.containsValue("11");
        table.remove(1, 3);
        System.out.println("7." + table.get(3, 4));

        Map<Integer, String> columnMap = table.column(1);
        Map<Integer, String> rowMap = table.row(2);

        System.out.println("8." + table
                + ", contains11:" + contains11
                + ", containColumn2:" + containColumn2
                + ", containsRow1:" + containsRow1
                + ", containsValue11:" + containsValue11
                + ", columnMap" + columnMap
                + ", rowMap" + rowMap);


    }

    public static void extensionCollertionUtils() {
        //Forwarding装饰器
        //PeekingIterator
        //AbstractIterator
        //AbstractSequentialIterator
    }


    public static void main(String[] args) {
        //immutableCollection();
        //guawaNewCollection();
        //newCollectionsUtils();
        //extensionCollertionUtils();

        List<Integer> list = new ArrayList<>();
        for (Integer v : list) {
            System.out.println(1);
        }


    }

}
