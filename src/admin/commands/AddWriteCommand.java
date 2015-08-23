package admin.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.NewsDAO;
import dto.News;

public class AddWriteCommand extends Command {

	static Logger logger = Logger.getLogger(AddWriteCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		News news = new News();
		NewsDAO dao = NewsDAO.getNewsDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		news.setId(id);
		news.setTitle(request.getParameter("title"));
		news.setCategory(request.getParameter("category"));
		news.setAnnotation(request.getParameter("annotation"));
		news.setAuthor(request.getParameter("author"));
		news.setDate(request.getParameter("date"));
		news.setText(request.getParameter("text"));
		dao.addNews(news);
		try {
			response.sendRedirect("AdminController");
		} catch (IOException ioe) {
			logger.error(ioe);
		}

	}

}
