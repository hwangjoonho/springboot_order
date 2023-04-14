package hello.core.member;

public class MemberServiceImpl implements MemberService{
    
                                                //  실제 할당부분이 구현체를 의존 DIP 원칙 위배
    // 현재 MemberServiceImpl은 MemberRepository 인터페이스와 MemoryMemberRepository 구현체 둘다 의존중이라 문제다
    private final MemberRepository memberRepository; //= new MemoryMemberRepository(); DIP, OCP위해 AppConfig에서 적용
    // 인터페이스만적용, 밖에서 적용되어 넣어줌

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
}
