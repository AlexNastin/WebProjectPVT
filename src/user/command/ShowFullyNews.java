package user.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.NewsDAO;
import dto.News;

public class ShowFullyNews extends Command {
	static Logger logger = Logger.getLogger(ShowFullyNews.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		NewsDAO newsDAO = NewsDAO.getNewsDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		News news = newsDAO.getNews(id);
		request.setAttribute("newsuser", news);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/SelectedUserNews.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
	}

}
