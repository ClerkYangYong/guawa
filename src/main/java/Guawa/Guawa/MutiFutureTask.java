package Guawa.Guawa;

import com.google.common.util.concurrent.*;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class MutiFutureTask {

    private static final int PoolSize = 20;

    //带有回调机制的线程池
    private static final ListeningExecutorService pool= MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(PoolSize));

    public static <T, V> List<V> batchExec(List<T> params, BatchFuture<T, V> batchFuture) {
        if(CollectionUtils.isEmpty(params)){
            return null;
        }
        final List<V> value = Collections.synchronizedList(new ArrayList<V>());
        try{
            List<ListenableFuture<V>> futures = new ArrayList<>();
            for(T t : params){
                //将实现了Callable的任务提交到线程池中，得到一个带有回调机制的ListenableFuture实例
                ListenableFuture<V> sfuture = pool.submit(new SingleTask<T, V>(t, batchFuture));
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
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(ExecutionException e){
            e.printStackTrace();
        }

        return value;
    }

    /**
     *业务实现类
     * @param <T>
     * @param <V>
     */

    private static class SingleTask<T, V> implements Callable<V> {
        private T param;
        private BatchFuture<T, V> batchFuture;
        public SingleTask(T param, BatchFuture<T, V> batchFuture){
            this.param = param;
            this.batchFuture = batchFuture;
        }

        @Override
        public V call() throws Exception {
            return batchFuture.callback(param);
        }
    }
    public interface BatchFuture<T,V>{
        V callback(T param);
    }

}
