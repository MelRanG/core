package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    //MyLogger를 찾을 수 있는 DL이 생성시접에 주입됨. 처음 생성할 때는 Provider로 받고 HTTP가 살아있을 때(logDemo가 호출될 때) 의존성 주입이 발생함
    //즉 처음에 의존성주입을 해야하는데 MyLogger는 request 스코프니 주입이 안돼서 provider로 대신 받음
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        //호출하면 myLogger를 찾아서 주입함.
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
