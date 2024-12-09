package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void finaAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(
                AutoAppConfig.class,
                DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "name", Grade.VIP);
        int dPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        Assertions.assertThat(dPrice).isEqualTo(1000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies){
            this.policyMap=policyMap;
            this.policies=policies;
            System.out.println(policyMap);
            System.out.println(policies);

        }

        public int discount(Member member, int i, String discountCode) {
            DiscountPolicy dp = policyMap.get(discountCode);
            int price = dp.discount(member,i);
            return price;
        }




    }
}
