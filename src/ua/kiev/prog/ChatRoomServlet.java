package ua.kiev.prog;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChatRoomServlet extends HttpServlet {
    private ChatRoomsList chrList = ChatRoomsList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String chatroom = req.getParameter("chatroom");
        int isnew = Integer.parseInt(req.getParameter("new"));


        String msg = null;
        if(isnew==0) {
            if(chrList.isChatRoomExist(chatroom)) {
                if(chrList.getChatRoom(chatroom).getUsersList().contains(login)) {
                    msg = "ok";
                    } else {
                    chrList.getChatRoom(chatroom).addUser(login);
                    msg = "ok";
                    }
                } else {
                msg = "Chatroom \"" + chatroom + "\" is absent. \n Do you want to add? (press Y to add or any other key to return)";
            }
        } else if (isnew==1) {
            chrList.addChatRoom(new ChatRoom(chatroom, login));
            msg = "Chatroom \"" + chatroom + "\" were added!";
        }
        resp.getWriter().println(msg);

    }
}