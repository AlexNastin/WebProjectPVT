package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import admin.commands.AddCommand;
import admin.commands.AddWriteCommand;
import admin.commands.Command;
import admin.commands.DeleteCommand;
import admin.commands.ShowCommand;
import admin.commands.UpdateCommand;
import admin.commands.UpdateWriteCommand;

/**
 * Servlet implementation class AdminControler
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	static Logger logger = Logger.getLogger(AdminController.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		Command com = null;

		if (operation == null) {
			com = new ShowCommand();
		} else if (operation.equals("add")) {
			com = new AddCommand();
		} else if (operation.equals("addwrite")) {
			com = new AddWriteCommand();
		} else if (operation.equals("delete")) {
			com = new DeleteCommand();
		} else if (operation.equals("update")) {
			com = new UpdateCommand();
		} else if (operation.equals("updatewrite")) {
			com = new UpdateWriteCommand();
		}else if (operation.equals("sortcat")) {
			request.setAttribute("sort", "category");
			com = new ShowCommand();
		}else if (operation.equals("sortdate")) {
			request.setAttribute("sort", "date");
			com = new ShowCommand();
		}
		com.execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
