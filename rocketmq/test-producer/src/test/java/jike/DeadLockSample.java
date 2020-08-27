package jike;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DeadLockSample extends Thread {
    private volatile String testVol;
    private String first;
    private String second;
    public DeadLockSample(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    public  void run() {
        synchronized (first) {
            System.out.println(this.getName() + " obtained: " + first);
            try {
                Thread.sleep(1000L);
                synchronized (second) {
                    System.out.println(this.getName() + " obtained: " + second);
                }
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }
    public void testVol () {
        testVol = "yes";
    }
    public static void main(String[] args) throws InterruptedException {
//        String lockA = "lockA";
//        String lockB = "lockB";
//        DeadLockSample t1 = new DeadLockSample("Thread1", lockA, lockB);
//        DeadLockSample t2 = new DeadLockSample("Thread2", lockB, lockA);
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//        AtomicInteger intger = null;
//        intger.incrementAndGet();
        test();
    }

    public static void test() {
        System.out.println("方法开始");
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap();
        map.computeIfAbsent("AaAa", key -> map.computeIfAbsent("BBBB", key2 -> 42));
        System.out.println("方法结束");
    }
}
