package hello.servlet.web.springmvc.v1;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class SpringMemberSaveControllerV1 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request) {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member saveMember = memberRepository.save(new Member(username, age));

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", saveMember);

        return mv;
    }
}
