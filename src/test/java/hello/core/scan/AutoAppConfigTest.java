package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {
    @Test
    void basic(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = "  + beanDefinitionName  + bean);
        }
        MemberService memberService = ac.getBean(MemberService.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

    }

    @Test
    void test2() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        OrderService orderService = ac.getBean(OrderService.class);
        System.out.println(orderService);
        Assertions.assertThat(orderService).isInstanceOf(OrderServiceImpl.class);
    }
}
