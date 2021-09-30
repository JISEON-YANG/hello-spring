package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * <기존의 index.html이 아닌 home이 출력되는 이유>
     * 화면 우선순위
     * (1) 요청
     * (2) 스프링 컨테이너에 관련 요청정보가 있는지 확인
     * (3) 없으면 static 폴더에서 찾게됨
     * */

    @GetMapping("/")
    public String home(){
        return "home";
    }

}
