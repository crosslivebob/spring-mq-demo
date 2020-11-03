package jav;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock READ_LOCK = lock.readLock();
    private static final ReentrantReadWriteLock.WriteLock WRITE_LOCK = lock.writeLock();


    public static void main(String[] args) {
        Runnable readRun = () -> {
            READ_LOCK.lock();
            System.out.println(Thread.currentThread().getName());
            READ_LOCK.unlock();
        };
        Runnable readRunUnLock = () -> {
            READ_LOCK.lock();
            System.out.println(Thread.currentThread().getName());
        };

        new Thread(readRun).start();
//        new Thread(readRunUnLock).start();

    }

    CopyOnWriteArrayList list;
}
