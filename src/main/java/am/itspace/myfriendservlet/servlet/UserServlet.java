package am.itspace.myfriendservlet.servlet;

import am.itspace.myfriendservlet.model.User;
import am.itspace.myfriendservlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = new ArrayList<>();
        users = userService.getAllUsers();
        req.setAttribute("users",users);
        req.getRequestDispatcher("/WEB-INF/users.jsp").forward(req,resp);
    }
}
