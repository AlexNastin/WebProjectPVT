package admin.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.NewsDAO;

public class DeleteCommand extends Command {
	static Logger logger = Logger.getLogger(DeleteCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		NewsDAO dao = NewsDAO.getNewsDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		dao.deleteNews(id);
		try {
			response.sendRedirect("AdminController");
		} catch (IOException ioe) {
			logger.error(ioe);
		}

	}

}
