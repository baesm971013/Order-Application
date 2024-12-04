package hello.core.Member;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    @Test
    void join(){
        Member member = new Member(1L, "a", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(2L);

        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
