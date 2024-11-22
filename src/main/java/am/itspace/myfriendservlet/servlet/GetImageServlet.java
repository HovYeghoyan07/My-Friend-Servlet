package am.itspace.myfriendservlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/getImg")
public class GetImageServlet extends HttpServlet {


    private final String IMG_FOLDER = "C:\\Users\\hovha\\IdeaProjects\\My-Friend-Servlet\\image\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String imgName = req.getParameter("imgName");
        File imgFile = new File(IMG_FOLDER + imgName);
        try (FileInputStream inputStream = new FileInputStream(imgFile)) {

            resp.setContentType("image/jpeg");
            resp.setContentLength((int) imgFile.length());

            OutputStream outputStream = resp.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
