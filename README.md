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

## 3일차
- 스프링에서 xml, JAVA코드 등 다양한 방식으로 빈을 설정할 수 있는 이유는 BenDefinition으로 추상화돼있기 때문이다. 역활과 구현을 구분하니 확장성에서 큰 강점이 있다.
- @Bean이 config.get()에 비해 가진 장점은 싱글턴이다. config.get()은 매번 새로운 객체를 생성하지만 @Bean은 싱글턴으로 생성해서 메모리 관리에 장점이 있다. 2일 차 때 예상했던 장점이 맞았다.
- Bean으로 생성 된 부분도 new OrderRepository()처럼 새로운 객체를 생성하는데 싱글턴을 보장할 수 있는 이유는 뭘까? 스프링에서는 @Bean으로 스프링 컨테이너에 빈을 등록하고 @Configuration 어노테이션이 붙은 클래스는 바이트 코드 조작 라이브러리인 CGLIB를 통해 자식 클래스(config명@cglib 라는 이름으로 자식클래스를 생성)를 생성하며 이 클래스를 빈에 등록한다. 빈을 생성할 경우 스프링 컨테이너에 생성된 빈이 있는지 확인하고 있다면 해당 빈을 리턴하고 아니라면 새로운 빈을 생성한다. 이 방법으로 new라고 생성해도 싱글턴을 보장한다.

## 4일차
- @Autowired로 의존관계 자동주입할 할 때 스프링의 동작방식은 다음과 같다. 먼저 모든 빈을 스프링 컨테이너에 등록한다. 이후 @Autowired라고 선언된 메소드를 찾고 해당 메소드의 인자가 스프링 컨테이너에 있다면 자동으로 주입한다.
- 스프링에는 생성자, 수정자, 필드주입방법이 있다. 이중 테스트하기 쉽고, 불변한 생성자 주입을 사용해야한다.
- @Autowired를 지양하고 생성자 주입을 사용하라는 말이 있는데 엄밀히 말하면 틀렸다. 생성자 주입자체가 @Autowired를 한 것과 같은 효과를 내기 때문에 필드 주입을 지양하고 생성자 주입을 사용하라가 맞는 표현이다. 생성자 주입에 @Autowired를 붙여도 정상적으로 작동한다.

## 5일차
- 여러개의 동일한 타입의 빈을 생성하는 경우가 있다. MainDB에 대부분의 로직이 있고 가끔 보조DB를 쓴다거나 등등.. 이럴 때 의존관계 주입에서 충돌이 나며, 충돌되지 않게 설정해야한다.
- @Qualifier("name") 으로 명시적으로 이름을 주고 사용할 때도 @Qualfier("name")을 주는 경우와 @Primary로 무조건 우선순위를 주는 방법이 있다. @Primary가 좀 더 편하다.
- @Qualified의 경우 name에 빈 이름이 틀리더라도 컴파일 단계에서 오류를 잡을 수 없기 때문에 어노테이션을 만들어서 사용한다.

## 6일차
- 모든 빈을 Map으로 받는 경우가 있다. 클라이언트에서 할인정책을 골라서 넘기면 map.get()으로 해당하는 빈을 꺼내서 리턴하는 방법도 있다.
- 생성과 초기화는 다른 작업이다. 메모리를 할당하고 객체를 생성하는 것이 생성이며, DB나 네트워크연결 및 파라미터를 세팅하는 것이 초기화단계다.
- 스프링은 빈을 생성하고 의존성을 주입한다. 의존성 주입이 되지 않은 상태에서 생성할 때 연결을 세팅하면 null값이 발생한다. 그래서 의존성 주입이 끝나고 초기화를 해야한다.
- 스프링에서 의존성주입이 끝나면 초기화 메소드를 콜백하고 사용한 후 빈 소멸 메소드를 콜백하고 소멸한다. 초기화나 종료는 위 과정에서 스프링어노테이션(@PostConstruct) 같은 것을 사용하면 된다.

## 7일차
- 빈 스코프는 스프링 빈이 존재하는 범위다. 싱글톤의 경우 스프링 컨텍스트가 생성될 때 모두 생성되고, 소멸될 때까지 스프링 컨텍스트에서 관리한다.
- 프로토타입 스코프의 경우 생성과 의존성주입까지만 관리한다. 사용자가 요청할 때 새로 생성하고 생성한 뒤 스프링 컨텍스트에서 사라진다. 소멸은 관리하지 않으며 요청이 올때 마다 새로운 인스턴스 빈을 생성한다.
- 싱글톤에서 프로토타입 빈을 사용하는경우는 프로토타입이 한 번만 생성된다. 사용할 때마다 생성하고 싶으면 Provider나 ObjectProvider를 사용해야한다.
- 자바와 스프링에서 제공하는 기능이 겹칠 때가 있다. 보통 스프링에서 제공하는게 편하고 다양하므로 스프링 기능을 사용하자.