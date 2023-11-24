package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig config = new AppConfig();

        MemberService memberService1 = config.memberService();
        MemberService memberService2 = config.memberService();
        //매번 다른 객체가 생성된다.
        assertThat(memberService1).isNotSameAs(memberService2);
    }
}
