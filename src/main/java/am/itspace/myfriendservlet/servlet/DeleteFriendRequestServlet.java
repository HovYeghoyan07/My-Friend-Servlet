package am.itspace.myfriendservlet.servlet;

import am.itspace.myfriendservlet.model.FriendRequest;
import am.itspace.myfriendservlet.service.FriendRequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteFriendRequest")
public class DeleteFriendRequestServlet extends HttpServlet {

    private FriendRequestService friendRequestService = new FriendRequestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        friendRequestService.deleteFriendRequest(id);
        resp.sendRedirect("/WEB-INF/friendResponses");
    }
}
