package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class NewUsersServlet extends HttpServlet {
    private UsersList userList = UsersList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String msg = null;
            userList.addUser(new User(login, password));
            msg = "Registration success! \n Hello " + login + "!";

        resp.getWriter().println(msg);

    }
}