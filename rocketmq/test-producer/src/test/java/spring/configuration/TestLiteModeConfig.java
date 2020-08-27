package spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

//@Component
//@Configuration
public class TestLiteModeConfig {
    @Bean
    public A a(B b) {
        return new A(b);
    }

    @Bean
    public B b() {
        return new B();
    }

}

class A {
    private B b;
    public A (B b) {
        System.out.println("B.hashCode：" + b.hashCode());
        this.b = b;
    }
}

class B {
    public B() {
        System.out.println("new B.hashCode：" + this.hashCode());
    }
}
