package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
        private final MyLogger myLogger; // request 어노테이션 때문에 아래처럼 변경필요 // // 프록시 사용시 사용가능
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    //        ObjectProvider 덕분에 ObjectProvider.getObject() 를 호출하는 시점까지 request scope 빈의 생성을 지연
    // todo 즉, Provider는 빈은 정상적으로 생성 사용시 DL로 컨테이너에서 새롭게 호출 + (request Scope에서) 지연 역할

    @RequestMapping("log-demo")
    @ResponseBody // 화면이 없을때 문자 바로 반환
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLogger.getClass());

        /* 추가됨 */
//        MyLogger myLogger = myLoggerProvider.getObject();
//        ObjectProvider 덕분에 ObjectProvider.getObject() 를 호출하는 시점까지 request scope 빈의 생성을 지연
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
