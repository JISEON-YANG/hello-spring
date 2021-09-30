package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }

    /**
     * RequsetParam을 입력했기 때문에 해당 내용을 보려면 아래의 주소형식으로 입력해야함
     * localhost:8080/hello-mvc?name=이름
     * 해당 입력 정보가 모델로 담기고 html 파일로 넘어가면서 화면에 보여지는 것
     * */

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    /**
     * @ResponseBody
     * http는 헤더부와 바디부로 나뉨
     * @ResponseBody는 그 바디부에 내가 데이터를 직접 넣어주겠다는 의미
     * */

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return  "hello" + name;
    }

    /**
     * 객체 hello로 리턴
     * JSON 방식으로 출력됨(키와 벨류)
     * http://localhost:8080/hello-api?name=spring!!
     * 출력결과 --> {"name":"spring!!"}
     * ==> 객체로 리턴하고 @ResponseBody를 붙이면 JSON방식 반환하는 것이 기본
     * */

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloapi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
