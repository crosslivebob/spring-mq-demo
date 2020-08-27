package jav;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class CountDownLatchTest {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(10);

        Runnable runnable = () -> {
            try {
                countDownLatch.await();
                System.out.println("do it - " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread t1 = new Thread(runnable);
        t1.start();

        Thread t2 = new Thread(runnable);
        t2.start();

        for (int i = 0; i < 10; i++) {
            countDownLatch.countDown();
        }

    }
}
