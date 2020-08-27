package jav;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("test run");
        };
        executorService.scheduleWithFixedDelay(runnable, 1000, 1000, TimeUnit.MILLISECONDS);
//        executorService.scheduleAtFixedRate(runnable, 1000, 1000, TimeUnit.MILLISECONDS);
    }
}
