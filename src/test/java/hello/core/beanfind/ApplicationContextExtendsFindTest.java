package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac
            = new AnnotationConfigApplicationContext(TestConfig.class);

    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();

        }
    }

    @Test
    @DisplayName("부모타입으로 조회시, 자식이 둘 이상이있으면 중복오류가 발생한다")
    void test1(){
        //DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        org.junit.jupiter.api.Assertions.assertThrows(
                NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }
    @Test
    @DisplayName("부모타입으로 조회시, 자식이 둘 이상이면 bean 이름을 지정")
    void test2(){
        DiscountPolicy dp = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        Assertions.assertThat(dp).isInstanceOf(RateDiscountPolicy.class);
    }
    @Test
    @DisplayName("부모타입으로 조회시, 자식이 둘 구체 빈타입 지정 좋지 않은 방법")
    void test3(){
        RateDiscountPolicy dp = ac.getBean(RateDiscountPolicy.class);
        Assertions.assertThat(dp).isInstanceOf(RateDiscountPolicy.class);
    }
    @Test
    @DisplayName("부모타입으로 전체조회")
    void test4(){
        Map<String,DiscountPolicy> bt= ac.getBeansOfType(DiscountPolicy.class);
        for (String key: bt.keySet()){
            System.out.println(bt.get(key));
        }
    }
    @Test
    @DisplayName("부모타입으로 전체조회 Object ")
    void test5(){
        Map<String, Object> bt= ac.getBeansOfType(Object.class);
        for (String key: bt.keySet()){
            System.out.println(bt.get(key));
        }
    }



}
