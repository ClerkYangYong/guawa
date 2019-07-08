package Guawa.Guawa;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 缓存
 */
public class Caches {


    /**
     * 缓存在很多场景下都是相当有用的。例如，计算或检索一个值的代价很高，并且对同样的输入需要不止一次获取值的时候，就应当考虑使用缓存。
     * 场景
     * 你愿意消耗一些内存空间来提升速度
     * 你预料到某些键会被查询一次以上
     * 缓存中存放的数据总量不会超出内存容量。(Guava Cache是单个应用运行时的本地缓存。它不把数据存放到文件或外部服务器。)
     * <p>
     * 比较：Guava Cache与ConcurrentMap很相似，但也不完全一样。最基本的区别是ConcurrentMap会一直保存所有添加的元素，
     * 直到显式地移除。相对地，Guava Cache为了限制内存占用，通常都设定为自动回收元素。在某些场景下，尽管LoadingCache 不回收元素，
     * 它也是很有用的，因为它会自动加载缓存
     */

    public static void caches() {
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                //设置并发级别为8，并发级别是指可以同时写缓存的线程数
                .concurrencyLevel(8)
                //设置缓存容器的初始容量为10
                .initialCapacity(10)
                //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                .maximumSize(100)
                //是否需要统计缓存情况,该操作消耗一定的性能,生产环境应该去除
                .recordStats()
                //设置写缓存后n秒钟过期
                .expireAfterWrite(2, TimeUnit.SECONDS)
                //设置读写缓存后n秒钟过期,实际很少用到,类似于expireAfterWrite
                //.expireAfterAccess(17, TimeUnit.SECONDS)
                //只阻塞当前数据加载线程，其他线程返回旧值
                //.refreshAfterWrite(13, TimeUnit.SECONDS)
                //设置缓存的移除通知
                .removalListener(notification -> {
                    System.out.println("3." + notification.getKey() + " " + notification.getValue() + " 被移除,原因:" + notification.getCause());
                })
                //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
                .build(new DemoCacheLoader());

        //模拟线程并发
        new Thread(() -> {
            //非线程安全的时间格式化工具
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            try {
                TimeUnit.SECONDS.sleep(5);
                String value = cache.get(1);
                System.out.println("4." + Thread.currentThread().getName() + " " + simpleDateFormat.format(new Date()) + " " + value);
                //缓存状态查看
                System.out.println("6." + cache.stats().toString());
            } catch (Exception ignored) {
            }
        }).start();

        new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            try {
                TimeUnit.SECONDS.sleep(10);
                String value = cache.get(1);
                System.out.println("5." + Thread.currentThread().getName() + " " + simpleDateFormat.format(new Date()) + " " + value);
                //缓存状态查看
                System.out.println("6." + cache.stats().toString());
            } catch (Exception ignored) {
            }
        }).start();

    }

    public static class DemoCacheLoader extends CacheLoader<Integer, String> {
        @Override
        public String load(Integer key) throws Exception {
            System.out.println("1." + Thread.currentThread().getName() + " 加载数据开始");

            Random random = new Random();
            System.out.println("2." + Thread.currentThread().getName() + " 加载数据结束");
            return "value:" + random.nextInt(10000);
        }
    }


    public static void main(String[] args) {
        //说明：当线程中没有数值是，先进去的线程会加载数据，其他线程等待，只有当前前程加载完之后其他线程才可以加载
        caches();

    }

}
