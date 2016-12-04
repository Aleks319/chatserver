package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersServlet extends HttpServlet {

    private UsersList userList = UsersList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");



        String msg = null;

        for (User u : userList) {
            if (u.getLogin().equals(login)) {
                if (!u.getPassword().equals(password)) {
                    msg = "Wrong password!";
                } else {
                    msg = "Hello " + login + "!";
                    userList.getUser(login).setStatus("Online");
                }
            }
        }
        if (msg==null) {
            msg = "This login is absent. \n Do you want register? (press Y to register or any other key to exit)";
        }

        resp.getWriter().println(msg);

    }
}