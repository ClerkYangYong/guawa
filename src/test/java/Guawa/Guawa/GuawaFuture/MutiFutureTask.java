package Guawa.Guawa.GuawaFuture;

import com.google.common.util.concurrent.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * Created by order
 * 2019/3/15 20:42
 */
public class MutiFutureTask<T, V> {

    private static final int PoolSize = 20;

    //带有回调机制的线程池
    private static final ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(PoolSize));


    public static <T, V> List<V> batchExec(List<T> params, MutiFutureTask.BatchFuture<T, V> batchFuture) {


        final List<V> value = Collections.synchronizedList(new ArrayList<V>());
        try {
            List<ListenableFuture<V>> futures = new ArrayList<ListenableFuture<V>>();
            for (T t : params) {
                //将实现了Callable的任务提交到线程池中，得到一个带有回调机制的ListenableFuture实例
                ListenableFuture<V> sfuture = service.submit(new MutiFutureTask.SingleTask<T, V>(t, batchFuture));

                Futures.addCallback(sfuture, new FutureCallback<V>() {
                    @Override
                    public void onSuccess(V result) {
                        value.add(result);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        throw new RuntimeException(t);
                    }
                });
                futures.add(sfuture);
            }
            ListenableFuture<List<V>> allAsList = Futures.allAsList(futures);
            allAsList.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    /**
     * 业务实现类
     *
     * @param <T>
     * @param <V>
     */

    private static class SingleTask<T, V> implements Callable<V> {
        private T param;
        private BatchFuture<T, V> batchFuture;

        public SingleTask(T param, BatchFuture<T, V> batchFuture) {
            this.param = param;
            this.batchFuture = batchFuture;
        }

        @Override
        public V call() throws Exception {
            return batchFuture.callback(param);
        }
    }

    public interface BatchFuture<T, V> {
        V callback(T param);
    }
}
