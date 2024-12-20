package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

public class ApplicationContextSameBeanFindTest {
    // Test용 bean
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }

    AnnotationConfigApplicationContext ac
            = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입")
    void findBeanByTypeDulicate(){
        org.junit.jupiter.api.Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () ->ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조히시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void test2(){
        MemberRepository memberRepository = ac.getBean("memberRepository1",MemberRepository.class);
        Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }
    @Test
    @DisplayName("특정 타입")
    void test3(){
        Map<String, MemberRepository> bt  = ac.getBeansOfType(MemberRepository.class);
        System.out.println(bt);
    }

}
