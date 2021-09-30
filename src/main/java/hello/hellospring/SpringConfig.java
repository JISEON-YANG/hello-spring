package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;

//@Configuration
public class SpringConfig {

    /**
     * 1) 자동으로 등록 @Autowired, @Service, @Repository
     * (1)필드주입: 권장 안함
     * @Autowired private MemberService memberService;
     * (2) setter 주입
     * setXXX, 누군가가 memberController을 호출했을 때, public으로 열려있어야 가능
     * (3) 생성자주입: 권장
     * @Autowired
     * public MemberController(MemberService memberService){
     *         this.memberService = memberService;
     * }
     * -----------------------------------------------------
     * 2) 직접 자바 Bean 등록 --> @Configuration 지정필요
     * 스프링이 뜰때 Config파일을 읽고 스프링 빈에 등록
     * memberservice로직을 호출해서 스프링 빈에 등록
     * */
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    /**
     * 생성자에 memberRepository를 넣어줘야함
     * SpringBean에 등록되어 있는 Repository를 MemberService에 넣어줌
     * ---------------------------------------------------------
     * DB를 사용하게 되면 MemoryMemberRepository()를 DB의 것으로 변경하면 됨
     * 자동주입을 사용하게 되면 곳곳의 코드를 수정해야하지만, Bean 생성은 이곳만 수정해주면 됨
     * */
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

}
