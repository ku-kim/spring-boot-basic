package kukim.tistory.com.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 0.1 아주 간단한 예
    @GetMapping("hello") // /hello 주소
    public String hello(Model model) {
        model.addAttribute("data", "helllo world");
        return "hello"; // hello.html 찾기 , 문자열 리턴하면 'viewResolover'가 화면 찾아 리턴함!
    }

    // 0.2 간단한 예 (클라이언트가 /kukim 으로 들어온다면, templates/test222.html 을 찾음, 해당 내용을 attribute 내용을 채워서 전송함
    @GetMapping("kukim")
    public String test2(Model model) {
        model.addAttribute("data2", "data2임");
        return "test222";
    }

    // 1. 정적 콘텐츠
    // resources/static 폴더의 hello-static.html 을 바로 보여준다.

    // 2. MVC와 템플릿 엔진 예
    // 클라이언트가 /hello-mvc로 들어온다
    @GetMapping("hello-mvc") // 클라이언트가
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // 3. API
    // 3.1 간단한 예
    // 클라이언트가 /hello-string?name=kukim 을 요청하면
    // 스프링 부트는 HTTP ReponseBody로 해당 string을 전송해준다.
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name + "ResponseBody API";
    }

    // 3.2 또 다른 예
    // 객체를 responsebody에 전송한 경우
    // 객체를 json으로 던져줌 (HttpMessageConverter가 JsonConveter or StringConveter 처리하여 데이터 바꾸고 전송함)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
