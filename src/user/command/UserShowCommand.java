package user.command;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.NewsDAO;
import dto.News;

public class UserShowCommand extends Command {
	static Logger logger = Logger.getLogger(UserShowCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		List<News> list;
		Iterator<News> Iterator = null;
		NewsDAO dao = NewsDAO.getNewsDAO();
		list = dao.getListNews();
		Iterator = list.iterator();

		StringBuffer st = new StringBuffer();
		st.append("<table border=2>");
		while (Iterator.hasNext()) {
			News news = Iterator.next();
			st.append("<tr><td>");
			st.append(" <a href=\"UserController?operationUser=show&id=");
			st.append(news.getId());
			st.append("\">" + news.getTitle() + "</a>");
			st.append("</td><td>");
			st.append(news.getAnnotation());
			st.append("</td><td>");
			st.append(news.getDate());
			st.append("</td><td>");
			st.append(news.getCategory());
			st.append("</td>");
		}
		st.append("</table>");
		request.setAttribute("news", st.toString());
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/UserPage.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException se) {
			logger.error(se);
		} catch (IOException ioe) {
			logger.error(ioe);
		}
	}

}
