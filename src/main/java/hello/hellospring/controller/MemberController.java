package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    /**
     *  MemberService를 new로 만들게 되면
     * 다른 컨트롤러에서 MemberService를 가져다 쓰는 경우에 불편
     * 여러개 인스턴스 생성할 필요없이 하나 생성 후 공유하는 것이 좋음
     * 스프링 컨테이너에 등록하면 가능
     * */

    /**
     * 스프링 컨테이너에 스프링 빈을 등록할때, 기본적으로 싱글톤으로 등록
     * 유일하게 하나만 등록해서 공유
     * 같은 스프링 빈이면 모두 같은 인스턴스
     * */

    /**
     * @Autowired로 연결
     * spring 컨테이너에서 memberService를 가져다가 연결해줌
     * --> @Service 찾아가 연결 (MemberService)
     * --> @Repository 찾아가 연결 (MemoryMemberRepository)
     *  == DI(의존관계 주입)
     *  생성자가 한개라면 @Autowired 생략가능, 그 이상은 불가
     *  ---------------------------------------------------
     *  자동주입 또는 @Bean 미생성 시에는 @Autowired가 작동하지 않음
     * */

    private final MemberService memberService;

    // 자동등록
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

}
