package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy; // 추상화인 interface에만 의존!!

    //생성자 1개일떄는 autowired 생략 가능함.

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        System.out.println("first");
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        System.out.println("next member repository");
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy){
        System.out.println("next discoutn poplicy");
        this.discountPolicy=discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        Order order = new Order(memberId, itemName, itemPrice, discountPrice);

        return order;
    }

    //for test
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

}
