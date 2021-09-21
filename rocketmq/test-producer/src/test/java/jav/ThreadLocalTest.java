package jav;

import org.junit.Test;

public class ThreadLocalTest {
//    private ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    @Test
    public void test() throws InterruptedException {
        threadLocal.set("parent thread!!!");
        System.out.println(threadLocal.get());
        Thread t = new Thread(() -> {
            System.out.println(threadLocal.get());
            threadLocal.set("child thread!!!");
            System.out.println(threadLocal.get());
        });
        t.start();
        Thread.sleep(2000);
        System.out.println(threadLocal.get());
    }
}
