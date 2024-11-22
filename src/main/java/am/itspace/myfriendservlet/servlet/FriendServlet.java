package am.itspace.myfriendservlet.servlet;

import am.itspace.myfriendservlet.model.User;
import am.itspace.myfriendservlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/friends")
public class FriendServlet extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> friends = userService.getFriends(((User) req.getSession().getAttribute("user")).getId());
        req.setAttribute("friends", friends);
        req.getRequestDispatcher("/WEB-INF/friends.jsp").forward(req, resp);
    }
}
