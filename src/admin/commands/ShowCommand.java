package admin.commands;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import dao.NewsDAO;
import dto.News;

public class ShowCommand extends Command {
	static Logger logger = Logger.getLogger(ShowCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		List<News> list;
		Iterator<News> Iterator = null;
		String sort = (String) request.getAttribute("sort");
		NewsDAO dao = NewsDAO.getNewsDAO();
		if (sort == null) {
			list = dao.getListNews();
			Iterator = list.iterator();
		} else if (sort.equals("category")) {
			String category = (String) request.getParameter("category");
			list = dao.getNewsOfCategory(category);
			Iterator = list.iterator();
		} else if (sort.equals("date")) {
			String date = (String) request.getParameter("date");
			list = dao.getNewsOfDate(date);
			Iterator = list.iterator();
		}

		StringBuffer st = new StringBuffer();
		String email = (String) session.getAttribute("email");
		st.append("User: " + email);
		st.append("<table border=2>");
		st.append("<a href=\"AdminController?operation=add");
		st.append("\">Add news</a>");
		while (Iterator.hasNext()) {
			News news = Iterator.next();
			st.append("<tr><td>");
			st.append(" <a href=\"AdminController?operation=update&id=");
			st.append(news.getId());
			st.append("\">" + news.getTitle() + "</a>");
			st.append("</td><td>");
			st.append(news.getAuthor());
			st.append("</td><td>");
			st.append(" <a href=\"AdminController?operation=sortdate&date=");
			st.append(news.getDate());
			st.append("\">" + news.getDate() + "</a>");
			st.append("</td><td>");
			st.append(" <a href=\"AdminController?operation=sortcat&category=");
			st.append(news.getCategory());
			st.append("\">" + news.getCategory() + "</a>");
			st.append("</td><td>");
			st.append(" <a href=\"AdminController?operation=delete&id=");
			st.append(news.getId());
			st.append("\">Delete</a></td></tr>");
			st.append("</td></tr>");
		}
		st.append("</table>");
		request.setAttribute("news", st.toString());
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/NewsPage.jsp");

		try {
			dispatcher.forward(request, response);
		} catch (ServletException se) {
			logger.error(se);
		} catch (IOException ioe) {
			logger.error(ioe);
		}
	}
}