package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
        //getBean하지않더라도 스프링컨테이너에서 Autowired를 자동으로 등록된다.
    }

    static class TestBean{
        @Autowired(required=false)//null이면 호출이 아예 안된다.
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired//호출은 되는데 null이다. 특정 파라미터만 null로받을 수 있다.
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean3 = " + noBean2);
        }

        @Autowired//Optional.empty로 받는다.
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
