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

import static org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRES_NEW;

/**
 * 测试事务相关
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestProducerApplication.class)
public class TestTx {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    @Transactional
    public void testInsert() {
        studentMapper.insertStudent(111L, "test", "test");
        testUpdate();
        throw new RuntimeException("33");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testUpdate() {
        studentMapper.updateStudent("testUpdate", 111L);
    }
}
