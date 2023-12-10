package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//의존성 주입 시점에 가짜 Proxy객체를 만들어서 주입시킨다. 실제 객체를 호출할 때 실제 빈을 호출한다. 가짜 프록시 객체는 원본 객체를 상속받았기 때문에 클라이언트는 원본인지 모르고 사용 가능
//가짜 객체는 scope와 전혀 관련이 없다. 어차피 호출이 발생하면 가짜가 새로운 빈을 주입하기 떄문. 그래서 가짜는 싱글톤처럼 동작한다.
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "]" + "[" +requestURL + "] " + message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}
