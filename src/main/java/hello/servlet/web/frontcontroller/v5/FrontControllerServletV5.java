package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adaptor.ControllerV3HandlerAdaptor;
import hello.servlet.web.frontcontroller.v5.adaptor.ControllerV4HandlerAdaptor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>(); //previous verison => private Map<String, ControllerV4> controllerMap = new HashMap<>();
    private final List<MyHandlerAdaptor> handlerAdaptors = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND );
            return;
        }

        MyHandlerAdaptor adapter = getHandlerAdapter(handler);
        ModelView mv = adapter.handle(request, response, handler);

        MyView view = getMyView(mv);
        view.render(mv.getModel(), request, response);
    }

    private void initHandlerMappingMap() {
        String v3BasePath = "/front-controller/v5/v3/members";
        handlerMappingMap.put(v3BasePath, new MemberListControllerV3());
        handlerMappingMap.put(v3BasePath + "/save", new MemberSaveControllerV3());
        handlerMappingMap.put(v3BasePath + "/new-form", new MemberFormControllerV3());

        String v4BasePath = "/front-controller/v5/v4/members";
        handlerMappingMap.put(v4BasePath, new MemberListControllerV4());
        handlerMappingMap.put(v4BasePath + "/save", new MemberSaveControllerV4());
        handlerMappingMap.put(v4BasePath + "/new-form", new MemberFormControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdaptors.add(new ControllerV3HandlerAdaptor());
        handlerAdaptors.add(new ControllerV4HandlerAdaptor());
    }

    private MyHandlerAdaptor getHandlerAdapter(Object handler) {
        for (MyHandlerAdaptor adaptor : handlerAdaptors) {
            if (adaptor.support(handler)) {
                return adaptor;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다: " + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyView getMyView(ModelView mv) {
//        return new MyView("WEB-INF/views/" + mv.getViewName() + ".jsp");
        return new MyView("/WEB-INF/views/" + mv.getViewName() + ".jsp");
    }

}
