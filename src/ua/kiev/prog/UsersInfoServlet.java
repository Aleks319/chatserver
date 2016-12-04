package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersInfoServlet extends HttpServlet {

    private UsersList userList = UsersList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String type = req.getParameter("type");

        String msg = null;
        String status = null;

        if(type.equals("user")) {
            status = userList.getUser(login).getStatus();
            msg = "User \"" + login + "\" is " + status;
        } else if (type.equals("list")) {
            msg = userList.getListUsers();
        } else if (type.equals("changestatus")) {
            userList.getUser(login).setStatus("Offline");
        } else if(type.equals("isexist")) {
            if(!userList.isUserExist(login)) {
                msg = "Such user not registered!";
            };
        }
        resp.getWriter().println(msg);
    }
}
