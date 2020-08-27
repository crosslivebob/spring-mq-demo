package jav;

import java.awt.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class CyclicBarrierTest {
    public static void test1() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10,
                () -> System.out.println("last one - " + Thread.currentThread().getName()));
        Runnable runnable = () -> {
            try {
                cyclicBarrier.await(3, TimeUnit.SECONDS);
                System.out.println("do it - " + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 9; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
    }

    public static void test2() {
        CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo(10);
        Runnable runnable = () -> {
            try {
                cyclicBarrierDemo.await();
                System.out.println("do it - " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
    }

    public static void main(String[] args) {
        test1();
    }
}

class CyclicBarrierDemo {
    private volatile Sync sync;

    CyclicBarrierDemo(int count) {
        sync = new Sync(count);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    private static final class Sync extends AbstractQueuedSynchronizer {
        private final int count;
        Sync(int count) {
           this.count = count;
           setState(count);
        }

        int getCount() {
            return getState();
        }

        @Override
        protected int tryAcquireShared(int acquires) {
            return (getState() == this.count) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int releases) {
            return (getState() == this.count);
        }
    }

}
