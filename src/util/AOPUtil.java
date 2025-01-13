package util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


// 동적 프록시 핸들러
public class AOPUtil implements InvocationHandler {
    private final Object target;

    public AOPUtil(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        Object result = method.invoke(target, args); // 실제 메서드 호출
        long endTime = System.nanoTime();
        System.out.println("Method [" + method.getName() + "] executed in " + ((endTime - startTime) / 1_000_000) + " milliseconds");
        return result;
    }

    public static Object createProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new AOPUtil(target)
        );
    }
}
