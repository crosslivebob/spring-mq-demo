package jav;

import java.util.Collections;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(10);
        Runnable runnable = () -> {
            try {
                semaphore.acquire();
                System.out.println("do it - " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 20; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
        for (int i = 0; i < 10; i++) {
//        Thread.sleep(3000);
            semaphore.release();
        }

        System.out.println("end no?");
    }
}
