package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy") //중복등록됐을 때 구분하게한다. 사용하는쪽에서도 이름을 지정해야한다.
//@Primary //중복등록됐을 때 @Primary가 있으면 최우선으로 가져온다.
@MainDiscountPolicy //어노테이션을 붙이면 컴파일단계에서 오류를 찾는다. Qualifier는 문자열이라 컴파일단계에서 찾지 못한다.
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }
        return 0;
    }
}
