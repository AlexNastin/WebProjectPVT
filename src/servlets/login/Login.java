package servlets.login;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import dao.NewsDAO;
import dto.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(Login.class);
	NewsDAO newsDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		super.init();
		newsDAO = NewsDAO.getNewsDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();

		User user = newsDAO.getUser(email);

		if (user == null) {
			logger.info("Login failed. This user does not exist.");
			try {
				response.sendRedirect("FailLogin.jsp");
			} catch (IOException ioe) {
				logger.error(ioe);
			}
		} else if (user != null) {
			String emailUser = user.getEmail();
			String passwordUser = user.getPassword();
			if (email.equals(emailUser) && password.equals(passwordUser)) {
				logger.info("Login completed successfully. Email and password matches");
				logger.info("User enter the system - " + emailUser);
				session.setAttribute("email", emailUser);
				try {
					response.sendRedirect("AdminController");
				} catch (IOException ioe) {
					logger.error(ioe);
				}
			} else {
				logger.info("Login failed. Email and password do not match");
				try {
					response.sendRedirect("FailLogin.jsp");
				} catch (IOException ioe) {
					logger.error(ioe);
				}

			}

		}
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
