package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    //배우는 연기만 해야지 배역을 고르면 안된다. 배역을 고르고, 조명을 세팅하는 건 별도의 공연기획자가 해야한다.
    //OrderServiceImpl은 구현체를 뭘로 할지 선택하면 안된다.
    private final MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        //난 모르겠고 할인은 니가(discountPolicy)알아서해라. 단일 책임 원칙
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
