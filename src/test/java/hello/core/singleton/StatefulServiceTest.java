package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {


    @Test
    void test1(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService s1 = ac.getBean("statefulService",StatefulService.class);
        StatefulService s2 = ac.getBean("statefulService",StatefulService.class);

        // 10000
        s1.order("userA",10000);
        s2.order("userB",20000);
        int price = s1.getPrice();
        System.out.println(price);
        Assertions.assertThat(price).isEqualTo(10000);

    }
    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}