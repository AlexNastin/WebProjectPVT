package admin.commands;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;

public class AddCommand extends Command {
	static Logger logger = Logger.getLogger(AddCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/AddNews.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

	}

}
