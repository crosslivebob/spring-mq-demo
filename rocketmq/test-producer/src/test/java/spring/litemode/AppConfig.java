package spring.litemode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AppConfig {

    @Bean
    public User user() {
        return new User();
    }

    @Bean
    public String name(User user) {
        System.out.println(user.hashCode());
        System.out.println(user().hashCode());
        return "123";
    }
}
