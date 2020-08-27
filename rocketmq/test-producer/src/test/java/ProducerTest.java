import org.demo.mq.TestProducerApplication;
import org.demo.mq.controller.TestProController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ConcurrentHashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestProducerApplication.class)
public class ProducerTest {

    @Autowired
    TestProController testProController;

    @Test
    public void testRest() {
        System.out.println(testProController);
    }

    @Test
    public void test() {
        ThreadLocal l1 = new ThreadLocal();
        Thread t1 = new Thread(() -> {
            l1.set("hello");
            System.out.println(l1.get());
        });

        Thread t2 = new Thread(() -> {
            l1.set("world");
            System.out.println(l1.get());
        });

        t1.start();
        t2.start();
        System.out.println("main：" + l1.get());
        ConcurrentHashMap map = new ConcurrentHashMap<>();
        map.put("", "");
    }

}
