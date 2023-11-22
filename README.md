# core
스프링 핵심 원리-느낀점

## 1일차
- 주문에서 할인을 자체적으로 계산하지 않고 따로 클래스를 만들어서 뺏다.
  만들 때는 번거로웠지만 할인만 테스트할 때 좋았다.
- 배우(인터페이스)는 연기만 해야지 배역을 고르거나 조명을 고르면 안된다. 이 작업은 별도의 공연기획자가 해야한다.
  OrderServiceImpl이라는 배우는 DB를 MemoryRepository를 쓸지 JDBCRepository를 쓸지 고르면 안된다. 외부에서 만들어서 가져와야한다.
  공연기획자는 AppConfig를 의미한다.

## 2일차
- AppConfig를 통해서 구성영역과 사용영역을 구분했다. 지금은 둘다 코드를 변경해야하지만 영역을 구분함으로 써 설정을 변경할 때 여기저기 흩어져있는 설정에서 코드를 찾아서 수정하지않고, 구성영역에서 바로 수정할 수 있다. 실제 코드를 수정하지 않고 구성영역만 수정해서 새로운 기능을 바꿀 수 있다는 장점
- 코드를 Spring형식으로 변경했는데 지금은 config.get()보다 ac.getBean()이 더 코드가 길다. 어떤 장점이 있는지 아직 모르지만 로그를 보니 싱글턴으로 모든 Bean이 생성되는데, config를 바로 사용하면 계속 새로 객체를 생성하고 ac방법은 싱글턴이라서 한 객체를 돌려쓴다는 장점이 있는걸까?
- Spring에서 빈을 생성하고 연결하는 과정은 다음과 같다. @Bean으로 지정된 모든 요소를 스프링 컨테이너 빈 저장소에 가져온다. 이후 Config설정에 따라서 의존관계를 연결한다. 즉 런타임시에 의존관계들이 설정된다.
