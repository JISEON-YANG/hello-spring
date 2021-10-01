package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // 회원가입
    // 중복 회원 안됨(이름)
    public Long join(Member member){
        /**
         * 메소드 호출시간 체크(기본적인 방법) --> 모든 메소드에 추가해줘야 함
         * long start = System.currentTimeMillis();
         * try{
         * 	// 중복회원 검증
         *             validateDuplicateMember(member);
         *             memberRepository.save(member);
         *             return member.getId();
         * }finally {
         *             long finish = System.currentTimeMillis();
         *             long timeMs = finish - start;
         *             System.out.println("join = " + timeMs + "ms");
         * }
         * -------------------------------------------------------
         * 모든 메소드에 추가하지 않고, AOP 적용
         * 호출시간을 측정하는 로직은 공통 관심 사항이다.
         * 원하는 곳에 시간 측정 로직을 생성한 후 필요할 때마다 사용한다.
         * --> TimeTraceAop.java 파일 생성
         * */
        // 중복회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        // m은 member을 뜻함
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
