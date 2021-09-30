package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import org.assertj.core.api.Assertions;

class MemberServiceTest {

    // 아래의 방법은 다른 인스턴스(리포지토리)라서 변경될 가능성이 있음
    // MemberService memberService = new MemberService();
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    // 같은 인스턴스 사용(Memberservice.java 변경), 아래 내용 추가 --> DI(dependency injection)
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        // 레포지토리에서 값 꺼내기
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복회원예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // 예시 1
/*      IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
*/

        // 예시 2
        try{
            memberService.join(member2);
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }


        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}