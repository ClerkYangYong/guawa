package Guawa.Guawa.JdkFuture;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by order
 * 2019/3/16 10:20
 * <p>
 * 查询商品价格
 */
public class PriceDemo {

    private List<Shop> shops = Arrays.asList(new Shop("shop1"),
            new Shop("shop2"),
            new Shop("shop3"),
            new Shop("shop4"),
            new Shop("shop5"),
            new Shop("shop6"),
            new Shop("shop7"),
            new Shop("shop8")
    );

    public List<String> findPrices(String product) {
        /*
         * 方法一：并行.
         * */
        return shops.stream().map(shop -> String.format("%s price is %.2f ", shop.getName(), shop.getPice(product)))
                .collect(Collectors.toList());

    }

    public List<String> findPrices1(String product) {
        /*
         * 方法二：CompletableFuture异步.
         * */
        //Executor executor = Executors.newCachedThreadPool();
        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100));
        List<CompletableFuture<String>> priceFuture = shops.stream().map(shop -> CompletableFuture
                .supplyAsync(() -> String.format("%s price is %.2f ", shop.getName(), shop.getPice(product)), executor))
                .collect(Collectors.toList());
        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        PriceDemo priceDemo = new PriceDemo();
        Long start = System.currentTimeMillis();
        System.out.println(priceDemo.findPrices("苹果x"));
        System.out.println("服务耗时：" + (System.currentTimeMillis() - start));

        PriceDemo priceDemo1 = new PriceDemo();
        Long start1 = System.currentTimeMillis();
        System.out.println(priceDemo1.findPrices1("苹果x"));
        System.out.println("服务耗时：" + (System.currentTimeMillis() - start1));

    }

}
