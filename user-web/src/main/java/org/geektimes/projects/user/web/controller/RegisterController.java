package org.geektimes.projects.user.web.controller;

import org.apache.commons.lang.StringUtils;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author Lin Zehuan
 */
@Path("/register")
public class RegisterController implements PageController {


    @Resource(name = "bean/UserService")
    private UserService userService;

    @GET
    @POST
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String email = request.getParameter("email");
        if (StringUtils.isBlank(email)) {
            return "register.jsp";
        }


        User user = new User();
        user.setEmail(email);
        user.setName("test");
        user.setPassword(request.getParameter("password"));
        user.setPhoneNumber("123345342");
        boolean result = userService.register(user);
        String message = result ? "成功" : "失败";
        System.out.println("message = " + message);
        return "result.jsp?message=" + message;
    }
}
