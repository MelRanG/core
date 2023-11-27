package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)//기존에 AppConfig는 제외하기 위해서 위 설정을 사용한다.
//컴포넌트 스캔은 최상단에 넣는 것이 좋고 @SpringBootApplication 안에 컴포넌트 스캔이 포함돼있다.
public class AutoAppConfig {
}
