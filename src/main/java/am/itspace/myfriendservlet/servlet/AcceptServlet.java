package am.itspace.myfriendservlet.servlet;

import am.itspace.myfriendservlet.model.FriendRequest;
import am.itspace.myfriendservlet.service.FriendRequestService;
import am.itspace.myfriendservlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/accept")
public class AcceptServlet extends HttpServlet {

    private FriendRequestService friendRequestService = new FriendRequestService();
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        FriendRequest friendRequest = friendRequestService.getFriendRequestByFromId(id);
        userService.acceptFriend(friendRequest.getFromId(), friendRequest.getToId());

    }
}
