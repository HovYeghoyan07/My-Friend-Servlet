package am.itspace.myfriendservlet.servlet;

import am.itspace.myfriendservlet.model.Message;
import am.itspace.myfriendservlet.model.User;
import am.itspace.myfriendservlet.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/sendMessage")
public class SendMessageServlet extends HttpServlet {
    MessageService messageService = new MessageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idFriend = Integer.parseInt(req.getParameter("id"));
        int idUser = ((User) req.getSession().getAttribute("user")).getId();
        List<Message> messages = messageService.getAllMessagesByUserAndFriendId(idUser, idFriend);
        req.setAttribute("messages", messages);
        req.setAttribute("idFriend",idFriend);
        req.getRequestDispatcher("/WEB-INF/sendMessage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idFriend = Integer.parseInt(req.getParameter("id"));
        int idUser = ((User) req.getSession().getAttribute("user")).getId();
        String message = req.getParameter("message");
        Date date=new Date();
        messageService.add(Message.builder()
                        .fromId(User.builder()
                                .id(idUser)
                                .build())
                        .toId(User.builder()
                                .id(idFriend)
                                .build())
                        .message(message)
                        .date(date)
                .build());
        resp.sendRedirect("/sendMessage?id="+idFriend);
    }
}
