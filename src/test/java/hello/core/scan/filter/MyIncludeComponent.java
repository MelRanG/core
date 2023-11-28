package hello.core.scan.filter;

import java.lang.annotation.*;

//자체 어노테이션 선언
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
