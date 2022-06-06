package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



/*@Component      // --> Component sacn 의 대상
@RequestMapping*/ // --> RequestMappingHandlerMapping의 대상 @Component 만 있으면 안되고 메소드가 아닌 클래스 레벨에 붙어 있어야 한다.

@Controller
@RequestMapping("/springmvc/v1")
public class SpringMemberFormControllerV1 {

    @RequestMapping("/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }


}
