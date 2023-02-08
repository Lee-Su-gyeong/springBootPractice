package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * 1. 파라미터 전송 기능
 * https://localhost:8081/request-param?username=hello&age=20
 **/
@WebServlet(name ="requestParamServlet", urlPatterns = "/requestParam")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ctrl +alt+ v
        Enumeration<String> parameterNames = req.getParameterNames();
        System.out.println("parameterNames = " + parameterNames);
        //자바 11
        //req.getParameterNames().asIterator().forEachRemaining(paramName -> System.out.println(paramName + "=" + req.getParameter(paramName)));
        //자바 8
        Map<String, String[]> parameterMap = req.getParameterMap();
        parameterMap.forEach((paramName, value) -> System.out.println("key: " + paramName + ", value: " + req.getParameter(paramName)));

        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();
        System.out.println("[단일 파라미터 조회]");
        String username = req.getParameter("username");
        System.out.println("request.getParameter(username) = " + username);
        String age = req.getParameter("age");
        System.out.println("request.getParameter(age) = " + age);
        System.out.println();
        //https://localhost:8081/request-param?usename=hello&age=20&usename=hello2
        System.out.println("[이름이 같은 복수 파라미터 조회]");
        System.out.println("request.getParameterValues(username)");
        String[] usernames = req.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username=" + name);
        }
        resp.getWriter().write("ok");

    }
}
