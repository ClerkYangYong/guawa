package Guawa.Guawa;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 区别于java线程，java线程在运行是不知道线程是或运行成功，guawa线程可以抓取线程运行时的异常
 * <p>
 * <p>
 * Futurex线程的弊端：两个异步的计算结果合并成一个，并且第二个依赖于第一个。等待Future中的结果全部执行完，
 * <p>
 * 最佳线程数目：最佳线程数目 = （线程等待时间与线程CPU时间之比 + 1）* CPU数目
 */
public class Concurrency {


    public static void main(String[] args) throws Exception {
        //testRateLimiter();
        //testListenableFuture();
        //testFuture();
        testGuawaFuture();

    }

    static class Task implements Callable<Integer> {
        String str;
        int i;

        public Task(String str, int i) {
            this.str = str;
            this.i = i;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("1.call execute.." + str);
            TimeUnit.SECONDS.sleep(1);
            return i;
        }
    }


    static ExecutorService pool = Executors.newFixedThreadPool(5);

    /**
     * RateLimiter类似于JDK的信号量Semphore，他用来限制对资源并发访问的线程数
     */
    public static void testRateLimiter() {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(pool);
        // 每秒不超过4个任务被提交
        RateLimiter limiter = RateLimiter.create(5.0);


        for (int i = 0; i < 10; i++) {
            // 请求RateLimiter, 超过permits会被阻塞
            limiter.acquire();


            final ListenableFuture<Integer> listenableFuture = executorService
                    .submit(new Task("is " + i, i));

            Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
                @Override
                public void onSuccess(Integer result) {
                    System.out.println("2.getlimiter result with callback " + result);
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }

        //线程的关闭，视情况而定
        pool.shutdown();
    }


    public static void testListenableFuture() {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(pool);
        List<Integer> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {

            final ListenableFuture<Integer> listenableFuture = executorService.submit(new Task("testListenableFuture", i));
            //同步获取调用结果
            try {
                System.out.println(listenableFuture.get());

            } catch (Exception e) {
                e.printStackTrace();
            }
            //第一种方式
            listenableFuture.addListener(new Runnable() {
                @Override
                public void run() {
                    try {
                        list.add(listenableFuture.get());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, executorService);

            //第二种方式
            Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
                @Override
                public void onSuccess(Integer result) {
                    list.add(result);
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }

        System.out.println(list.toArray());

    }


    public static void testFuture() throws Exception {
        // 创建线程池
        ExecutorService service = Executors.newCachedThreadPool();

        Long t1 = System.currentTimeMillis();

        // 任务1
        Future<Boolean> booleanTask = service.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Thread.sleep(1000);
                return true;
            }
        });

        while (true) {
            if (booleanTask.isDone() && !booleanTask.isCancelled()) {
                Boolean result = booleanTask.get();
                System.err.println("BooleanTask: " + result);
                break;
            }
        }

        // 任务2
        Future<String> stringTask = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "Hello World";
            }
        });
        while (true) {
            if (stringTask.isDone() && !stringTask.isCancelled()) {
                String result = stringTask.get();
                System.err.println("StringTask: " + result);
                break;
            }
        }

        // 任务3
        Future<Integer> integerTask = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);
                return new Random().nextInt(100);
            }
        });
        while (true) {
            if (integerTask.isDone() && !integerTask.isCancelled()) {
                Integer result = integerTask.get();
                System.err.println("IntegerTask: " + result);
                break;
            }
        }
        // 执行时间
        System.err.println("time: " + (System.currentTimeMillis() - t1));
    }

    public static void testGuawaFuture() throws ExecutionException, InterruptedException {

        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        Long t1 = System.currentTimeMillis();
        // 任务1
        ListenableFuture<Boolean> booleanTask = service.submit(() -> {
            Thread.sleep(1000);
            return true;
        });

        Futures.addCallback(booleanTask, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                System.err.println("BooleanTask: " + result);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

        // 任务2
        ListenableFuture<String> stringTask = service.submit(() -> {
            Thread.sleep(1000);
            return "Hello World";
        });

        Futures.addCallback(stringTask, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    if (booleanTask.get()) {
                        System.out.println(1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.err.println("StringTask: " + result);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

        // 任务3
        ListenableFuture<Integer> integerTask = service.submit(() -> {
            Thread.sleep(1000);
            return 10;
        });

        Futures.addCallback(integerTask, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.err.println("IntegerTask: " + result);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });


        // 执行时间
        System.err.println("time: " + (System.currentTimeMillis() - t1));
    }

}
