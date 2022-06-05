package hello.servlet.web.frontcontroller.v5.adaptor;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdaptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class ControllerV4HandlerAdaptor implements MyHandlerAdaptor {

    @Override
    public boolean support(Object handler) {
        return handler instanceof ControllerV4; // return (handler instanceof ControllerV4);
     }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws SecurityException, IOException {

        ControllerV4 controller = (ControllerV4) handler;

        HashMap<String, String> paramMap = createParam(request);
        HashMap<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);

        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }

    private HashMap<String, String> createParam(HttpServletRequest request) {
        HashMap<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
