package hello.core.member;

import hello.core.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl(); // appConfig 참조로 변경
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // AppConfig 설정정보를 스프링 컨테이너에 넣어준다.
        MemberService memberService = applicationContext.getBean(" memberService", MemberService.class);


        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(member.getId());
        System.out.println("new member : " + member.getName());
        System.out.println("findMember : " + findMember.getName());

    }
}
