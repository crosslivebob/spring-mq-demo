package jav;

import java.util.BitSet;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ThreadTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        ExecutorService service = Executors.newFixedThreadPool(10);
        ExecutorService service = new ThreadPoolExecutor(1, 3,
                10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1));
//        Future<Integer> future = service.submit(() -> {
//            return 11;
//        });
        System.out.println();
        String o = "keyi";
        Future future = service.submit(() -> {
            System.out.println(111);
        }, o);
        IntStream.range(0, 10).forEach(i -> {
            service.submit(() -> {
                System.out.println("测试线程" + i);
            });
        });

        System.out.println(future.get());
//        service.shutdown();

//        FutureTask<String> task = new FutureTask<>(() -> {
//            System.out.println(11);
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("user or daemon thread is over!!!");
//        }, "This is a test");
//        Thread t = new Thread(task);
//        t.start();
////        String result = task.get();
////        System.out.println(result);
//        System.out.println("main thread is over");
    }
}
