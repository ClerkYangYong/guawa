package Guawa.Guawa.JdkFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by order
 * 2019/3/16 10:17
 */
public class Shop {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Shop(String name) {
        this.name = name;
    }


    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    Random random = new Random();

    public double getPice(String product) {
        delay();
        return random.nextDouble() * 100;
    }

    public Future<Double> getPriceAsync(String product) {

        /*
         * supplyAsync()该方法对线程异常进行处理，避免出现异常后的堵塞
         * */
        return CompletableFuture.supplyAsync(() -> (getPice(product)));
    }

    public static void main(String[] args) throws Exception {
        Shop show = new Shop("bestShow");

        long start = System.currentTimeMillis();

        Future<Double> futurePrice = show.getPriceAsync("some product");
        System.out.println("调用返回，耗时" + (System.currentTimeMillis() - start));
        double price = futurePrice.get();
        System.out.println("价格返回，耗时" + (System.currentTimeMillis() - start));

    }
}
