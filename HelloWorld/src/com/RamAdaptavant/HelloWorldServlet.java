package com.RamAdaptavant;
import java.io.IOException;
import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
@Controller
public class HelloWorldServlet extends HttpServlet {
    
	@RequestMapping(value="/")
	public void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		resp.sendRedirect("/index.html");
	}
	@RequestMapping(value="/login")
	public void redirect1(HttpServletRequest req, HttpServletResponse resp) throws IOException
		{
			
		resp.sendRedirect("/HelloWorld.jsp");
		}
	
		
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
              throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        if (user != null) {
            resp.setContentType("text/plain");
            resp.getWriter().println("Hello, " + user.getNickname());
        } else {
            resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
        }
    }
}
