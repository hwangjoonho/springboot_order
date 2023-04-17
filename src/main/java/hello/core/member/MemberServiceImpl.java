package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    
                                                //  실제 할당부분이 구현체를 의존 DIP 원칙 위배
    // 현재 MemberServiceImpl은 MemberRepository 인터페이스와 MemoryMemberRepository 구현체 둘다 의존중이라 문제다
    private final MemberRepository memberRepository; //= new MemoryMemberRepository(); DIP, OCP위해 AppConfig에서 적용
    // 인터페이스만적용, 밖에서 적용되어 넣어줌

    // @ComponentScan 사용시 의존관계 주입을 위해 @Autowired 사용
    @Autowired  // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
