package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient/* implements InitializingBean *//*초기화 빈*//* , DisposableBean *//*소멸전 빈*/{

    private String url;

    // 객체 생성 부분
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message : "+message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }


    /*--------------------------------------------------*/
//    이 인터페이스는 스프링 전용 인터페이스다. 해당 코드가 스프링 전용 인터페이스에 의존한다

//    @Override   // 초기화 콜백 :  빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
//    public void afterPropertiesSet() throws Exception { // 의존 관계 주입 후 호출하겠다
    @PostConstruct
    public void init() {    // 빈 등록 초기화, 소멸 메서드 지정
        // 객체의 생성과 초기화를 분리하자.
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지"); 
    }

//    @Override   // 소멸전 콜백 : 빈이 소멸되기 직전에 호출
//    public void destroy() throws Exception {
    @PreDestroy
    public void close(){    // 빈 등록 초기화, 소멸 메서드 지정
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
