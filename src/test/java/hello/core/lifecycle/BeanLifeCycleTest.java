package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

//    스프링 빈의 이벤트 라이프사이클
//    스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료

    @Test
    public void lifeCycleTest() {
        // 스프링 빈 생성 + 의존 관계 주입(생성자) + 의존 관계 주입(기타)
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        // 사용
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); // 스프링 컨테이너를 종료, ConfigurableApplicationContext 필요
    }

    @Configuration  // 스프링 빈 생성 단계
    static class LifeCycleConfig {
//        @Bean(initMethod = "init", destroyMethod = "close") // 빈 등록 초기화, 소멸 메서드 지정
        //코드를 고칠 수 없는 외부 라이브러리를 초기화, 종료해야 하면 @Bean 의 initMethod , destroyMethod를 사용
        @Bean
        public NetworkClient networkClient() {
            // 객체의 생성과 초기화를 분리하자.
            System.out.println("객체 생성 단계");
            NetworkClient networkClient = new NetworkClient();  //객체 생성 후 밑에서 값을 넣어준다 이렇게 따로 기능 분리 필요
            System.out.println("의존 관계 주입 단계 = 초기화 단계");
            networkClient.setUrl("http://hello-spring.dev");    // 의존 관계 주입 단계
            return networkClient;
        }
    }
}
