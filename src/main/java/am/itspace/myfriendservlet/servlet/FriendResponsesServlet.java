package am.itspace.myfriendservlet.servlet;

import am.itspace.myfriendservlet.model.FriendRequest;
import am.itspace.myfriendservlet.model.User;
import am.itspace.myfriendservlet.service.FriendRequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/friendResponses")
public class FriendResponsesServlet extends HttpServlet {
    FriendRequestService friendRequestService = new FriendRequestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FriendRequest> friendRequests = friendRequestService.getAllFriendRequestByToId(((User)req.getSession().getAttribute("user")).getId());
        req.setAttribute("friendRequests", friendRequests);
        req.getRequestDispatcher("/WEB-INF/friendResponses.jsp").forward(req, resp);
    }
}
