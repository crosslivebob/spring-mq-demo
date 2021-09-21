package spring.tx;

import org.demo.mq.TestProducerApplication;
import org.demo.mq.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRES_NEW;

/**
 * 测试事务相关
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = TestProducerApplication.class)
public class TestTx {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    @Transactional
    public void testInsert() {
        studentMapper.insertStudent(444L, "test", "test");
        testUpdate();
//        throw new RuntimeException("33");
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testUpdate() {
        try {
            studentMapper.insertStudent(20180101L, "test", "test");
//            studentMapper.updateStudent("testUpdate", 111L);
        } catch (Exception e) {
            System.out.println("测试");
        }
    }

    @Test
    public void testSetRetailAll() {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

//        List<String> set1 = new ArrayList<>();
//        List<String> set2 = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            set1.add(i + "");
        }
        for (int i = 30000; i < 200000; i++) {
            set2.add(i + "");
        }
        Long now = System.currentTimeMillis();
        set1.retainAll(set2);
        System.out.println(System.currentTimeMillis() - now);

    }
}
