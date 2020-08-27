package spring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import spring.model.TestBean;

@Configuration
@EnableAspectJAutoProxy
@Import(TestBean.class)
public class TestConfiguration {

}
