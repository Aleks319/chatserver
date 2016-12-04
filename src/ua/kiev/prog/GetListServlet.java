package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetListServlet extends HttpServlet {
	
	private MessageList msgList = MessageList.getInstance();

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String fromStr = req.getParameter("from");
		String login = req.getParameter("login");
		int from = 0;
		try {
			from = Integer.parseInt(fromStr);
		} catch (Exception ex) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
		}

		List<Message> list = msgList.getList();
		String json;
		int newN = 0;
		if(from == list.size()) {
			json = null;
			newN = list.size();
		} else {
			JsonMessages jm = new JsonMessages(list, from, login);
			Gson gson = new GsonBuilder().create();
			json = gson.toJson(jm);
			newN = list.size();
		}

		if(login.endsWith("_GET_N")) {
			resp.getWriter().println(newN);
		} else {
			if (json != null) {
				OutputStream os = resp.getOutputStream();
				byte[] buf = json.getBytes(StandardCharsets.UTF_8);
				os.write(buf);
			}
		}
	}
}