package Guawa.Guawa;

import com.google.common.reflect.Reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 反射
 */

public class GuawaReflection {

    public static void main(String[] args) {
        InvocationHandler invocationHandler = new MyInvocationHandler();

        // Guava 动态代理 写法比较简单
        IFoo foo = Reflection.newProxy(IFoo.class, invocationHandler);
        foo.doSomething();

        //jdk 动态代理 写法比较复杂
        IFoo jdkFoo = (IFoo) Proxy.newProxyInstance(
                IFoo.class.getClassLoader(),
                new Class<?>[]{IFoo.class},
                invocationHandler);
        jdkFoo.doSomething();
    }

    public static class MyInvocationHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            System.out.println("proxy println something");
            return null;
        }
    }

    public static interface IFoo {
        void doSomething();
    }

}
