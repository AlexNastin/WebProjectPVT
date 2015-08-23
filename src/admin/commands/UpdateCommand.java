package admin.commands;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.NewsDAO;
import dto.News;

public class UpdateCommand extends Command {
	static Logger logger = Logger.getLogger(UpdateCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		News news = new News();
		NewsDAO newsDAO = NewsDAO.getNewsDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		news = newsDAO.getNews(id);
		request.setAttribute("news", news);

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/UpdateNews.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

	}

}
