package Guawa.Guawa;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;

/*基本工具*/

public class BasicUtils {

    /*
     * 使用和避免null
     * 原因:null值所代表的语义很含糊，例如，Map.get(key)返回Null时，可能表示map中的值是null，亦或map中没有key对应的值。
     * Null可以表示失败、成功或几乎任何情况。使用Null以外的特定值，会让你的逻辑描述变得更清晰。链接：http://ifeve.com/google-
     * guava-using-and-avoiding-null/
     * 表示:guawa中使用Optional标识可能为null的T类型引用
     * 目的是为了防止NULL的出现，强制性的让你去判断当期引用是否为null
     *
     * jdk1.8中也使用Optional对null值的判断
     */
    public static void useOptional() {
        Integer a = 5;

        //创建指定引用的Optional实例，若引用为null则快速失败
        Optional<Integer> possible = Optional.of(5);
        //如果Optional包含非null的引用（引用存在），返回true
        System.out.println(possible.isPresent());
        //返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException
        System.out.println(possible.get());
        //返回Optional所包含的引用，若引用缺失，返回指定的值
        System.out.println(possible.or(a));
        //返回Optional所包含的引用，若引用缺失，返回null
        System.out.println(possible.orNull());

        possible.or(5);

    }

    /**
     * Preconditions前置条件用于判断传参或者数值,可以间接取消冗长的if判断.方法静态导入，自定义异常。this.field = checkNotNull(field).减少代码量
     */
    public static void usePrecondition() {
        //未使用前置条件
        /*public void login (String userName, String password){
            if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
                throw new RuntimeException("用户名或密码不能为空");
            }
            User user = userService.queryUserByUserNameAndPassword(userName, password);
            if (null == user) {
                throw new RuntimeException("用户名或密码错误");
            }
        }*/

        //使用前置条件
       /* public void login (String userName, String password){
            Preconditions.checkArgument(!(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)), "用户名或密码不能为空");
            User user = userService.queryUserByUserNameAndPassword(userName, password);
            Preconditions.checkNotNull(user, "dingdan {}",orderon);
        }*/


    }

    /**
     * guawa的Objects类，会对对象的equals和hashCode以及toString方法进行优化
     */
    public static void useObjectMethod() {
        Integer a = 1;
        System.out.println(Objects.equal("a", 1));
        //使用hashCode做散列值计算性能比较突出
        //使用Objects.toStringHelper编写toString方法，调试时候使用
        System.out.println(
                Objects.toStringHelper("MyObject")
                        .add("x", 1)
                        .add("y", 3)
                        .toString());
    }

    /**
     * guawa的比较器，Fluent编程风格。示例：a.method().method();
     */
    public static void useComparator() {

        List<Integer> a = new ArrayList<>();
        List<Integer> listtest = Lists.newArrayListWithCapacity(10);
        listtest.add(1);
        listtest.add(1);
        listtest.add(1);
        listtest.add(2);
        System.out.println("listtest:" + listtest);

        //Ordering.natural()使用Comparable类型的自然顺序， 例如：整数从小到大，字符串是按字典顺序
        Ordering<Integer> ordering = Ordering.natural();

        //isOrdered()是否有序，Iterable不能少于2个元素
        System.out.println("1:" + ordering.isOrdered(listtest));
        //isStrictlyOrdered是否严格有序。请注意，Iterable不能少于两个元素
        System.out.println("2:" + ordering.isStrictlyOrdered(listtest));
        //问题：两个的区别；isOrdered是左边的值大于右边的值，isStrictlyOrdered是左边的大于等于右边的值。意味着从小到大一次的排列

        List<Integer> listReduce = Lists.newArrayList();
        for (int i = 9; i > 0; i--) {
            listReduce.add(i);
        }
        //sortedCopy指定的元素作为一个列表的排序副本
        System.out.println("3:" + "ordering:" + ordering.sortedCopy(listReduce));
        System.out.println("4:" + "listReduce:" + listReduce);
        System.out.println("5:" + ordering.isOrdered(ordering.sortedCopy(listReduce)));
        System.out.println("6:" + ordering.isStrictlyOrdered(ordering.sortedCopy(listReduce)));


        Ordering<String> natural = Ordering.natural();

        /* 不可变集合，顾名思义就是说集合是不可被修改的。集合的数据项是在创建的时候提供，并且在整个生命周期中都不可改变。
        　 为什么要用immutable对象？immutable对象有以下的优点：
            1.对不可靠的客户代码库来说，它使用安全，可以在未受信任的类库中安全的使用这些对象
            2.线程安全的：immutable对象在多线程下安全，没有竞态条件
    　　　　 3.不需要支持可变性, 可以尽量节省空间和时间的开销. 所有的不可变集合实现都比可变集合更加有效的利用内存 (analysis)
    　　　　 4.可以被使用为一个常量，并且期望在未来也是保持不变的
    　　    immutable对象可以很自然地用作常量，因为它们天生就是不可变的对于immutable对象的运用来说，它是一个很好的防御编程（defensive programming）的技术实践。
        */
        List<String> abc = ImmutableList.of("a", "b", "c");
        System.out.println("7:" + natural.isOrdered(abc));
        System.out.println("8:" + natural.isStrictlyOrdered(abc));
        System.out.println("9:isOrdered reverse :" + natural.reverse().isOrdered(abc));

        List<String> cba = ImmutableList.of("c", "b", "a");
        System.out.println("10:" + natural.isOrdered(cba));
        System.out.println("11:" + natural.isStrictlyOrdered(cba));

        //max返回集合中最大的元素
        System.out.println("12:max:" + natural.max(cba));
        //min返回集合中最小的元素
        System.out.println("13:min:" + natural.min(cba));
        //返回指定的第k个可迭代的最小的元素，按照这个从最小到最大的顺序。
        System.out.println("14:leastOf:" + natural.leastOf(cba, 12));
        //返回指定的第k个可迭代的最大的元素，按照这个从最大到最小的顺序。
        System.out.println("15:greatestOf:" + natural.leastOf(cba, 2));


        List<String> list = Lists.newArrayList();
        list.add("peida");
        list.add("jerry");
        list.add("harry");
        list.add("eva");
        list.add("jhon");
        list.add("neron");

        Ordering<String> naturalOrdering = Ordering.natural();

        System.out.println("16:naturalOrdering:" + naturalOrdering.sortedCopy(list));
        System.out.println("17:leastOf list:" + naturalOrdering.leastOf(list, 3));
        System.out.println("18:greatestOf:" + naturalOrdering.greatestOf(list, 3));
        System.out.println("19:reverse list :" + naturalOrdering.reverse().sortedCopy(list));
        System.out.println("20:isOrdered list :" + naturalOrdering.isOrdered(list));
        System.out.println("21:isOrdered list :" + naturalOrdering.reverse().isOrdered(list));
        list.add(null);
        list.add(null);
        System.out.println("22: add null list:" + list);
        //null往前放
        System.out.println("23:nullsFirst list :" + naturalOrdering.nullsFirst().sortedCopy(list));
        //null往后放
        System.out.println("24:nullsLast list :" + naturalOrdering.nullsLast().sortedCopy(list));
    }

    /**
     * guawa异常，暂时未研究
     */

    public static void useException() {

    }


    public static void main(String arge[]) {
        //useOptional();
        //useAndEludeNull();
        //usePrecondition();
        //useObjectMethod();
        useComparator();


    }

}
