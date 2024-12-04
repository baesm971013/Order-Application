package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10프로 할인이 적용되어야한다")
    void vip_o(){
        Member member = new Member(1L, "memberA", Grade.VIP);

        int discount = discountPolicy.discount(member,10000);

        Assertions.assertThat(discount).isEqualTo(1000);

    }
    @Test
    @DisplayName("If is not vip no discounts should be applied")
    void vip_x(){
        Member member = new Member(1L, "memberA", Grade.BASIC);

        int discount = discountPolicy.discount(member,10000);

        Assertions.assertThat(discount).isEqualTo(0);
    }

}