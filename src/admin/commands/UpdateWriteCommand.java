package admin.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.NewsDAO;

public class UpdateWriteCommand extends Command {
	static Logger logger = Logger.getLogger(UpdateWriteCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		NewsDAO newsDAO = NewsDAO.getNewsDAO();

		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String annotation = request.getParameter("annotation");
		String author = request.getParameter("author");
		String date = request.getParameter("date");
		String text = request.getParameter("text");
		newsDAO.updateNews(id, title, category, annotation, author, date, text);
		try {
			response.sendRedirect("AdminController");
		} catch (IOException ioe) {
			logger.error(ioe);
		}

	}
}
