package am.itspace.myfriendservlet.servlet;


import am.itspace.myfriendservlet.model.User;
import am.itspace.myfriendservlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserService();
    private static final String IMG_FOLDER = "C:\\Users\\hovha\\IdeaProjects\\My-Friend-Servlet\\image\\";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        StringBuilder msgBuilder = new StringBuilder();

        if (name == null || name.trim().isEmpty()) {
            msgBuilder.append("Please enter your Name");
        }

        if (surname == null || surname.trim().isEmpty()) {
            msgBuilder.append("<br>");
            msgBuilder.append("Please enter your Surname");
        }
        if (email == null || email.trim().isEmpty()) {
            msgBuilder.append("<br>");
            msgBuilder.append("Please enter your Email");
        }

        if (password == null || password.trim().isEmpty() || password.length() > 6) {
            msgBuilder.append("<br>");
            msgBuilder.append("Please enter your Password");
        }


        Part img = req.getPart("img");
        String imgName = System.nanoTime() + "_" + img.getSubmittedFileName();
        img.write(IMG_FOLDER + imgName);

        if (img == null) {
            msgBuilder.append("<br>");
            msgBuilder.append("Please select an image");
        }


        if (userService.getUserByEmail(email) != null) {
            msgBuilder.append("<br>");
            msgBuilder.append("Your account has been registered with this email");
        } else if (!msgBuilder.isEmpty()) {
            req.setAttribute("msg", msgBuilder.toString());
            req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
        } else {
            User user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .imageName(imgName)
                    .build();
            userService.add(user);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/");
        }
    }
}
