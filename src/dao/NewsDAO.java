package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dto.News;
import dto.User;

public class NewsDAO implements DAO {

	static Logger logger = Logger.getLogger(NewsDAO.class);

	private Connection connection;
	private static NewsDAO newsDAO;

	private NewsDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
			logger.error("Error loading JDBC driver", cnfe);
		}
		BufferedReader bufferedReader = null;
		String infoDB = "C:/Java/workspace/WebProjectPVTAlexNastin/WebContent/WEB-INF/infoDB.txt";
		String dbURL = "";
		String user = "";
		String password = "";
		try {
			bufferedReader = new BufferedReader(new FileReader(infoDB));
			dbURL = bufferedReader.readLine();
			user = bufferedReader.readLine();
			password = bufferedReader.readLine();
			bufferedReader.close();
			connection = DriverManager.getConnection(dbURL, user, password);
			logger.info("Connection is created");
		} catch (FileNotFoundException fnfe) {
			logger.error("File not found", fnfe);
		} catch (IOException ioe) {
			logger.error("Error reading file", ioe);
		} catch (SQLException sqle) {
			logger.error("The connection is not created", sqle);
		}
	}

	public static synchronized NewsDAO getNewsDAO() {
		if (newsDAO == null) {
			newsDAO = new NewsDAO();
		}
		return newsDAO;
	}

	@Override
	public User getUser(String email) {
		User user = null;
		try {
			Statement statement = connection.createStatement();
			String queryGetUser = "SELECT * FROM users WHERE email=\"" + email
					+ "\"";
			ResultSet resultSet = statement.executeQuery(queryGetUser);
			if (resultSet.next()) {
				user = new User();
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setName(resultSet.getString("name"));
				user.setSurname(resultSet.getString("surname"));
				logger.info("Statement getUser executed");
			} else
				return null;
		} catch (SQLException sqle) {
			logger.error("The problem with the statement getUser", sqle);
		}
		return user;
	}

	@Override
	public News getNews(int id) {

		News news = null;
		try {
			Statement statement = connection.createStatement();
			String queryGetNews = "SELECT * FROM news WHERE id=" + id + "";
			ResultSet resultSet = statement.executeQuery(queryGetNews);
			if (resultSet.next()) {
				news = new News();
				news.setId(resultSet.getInt("id"));
				news.setTitle(resultSet.getString("title"));
				news.setCategory(resultSet.getString("category"));
				news.setAnnotation(resultSet.getString("annotation"));
				news.setAuthor(resultSet.getString("author"));
				news.setDate(resultSet.getString("date"));
				news.setText(resultSet.getString("text"));
				logger.info("Statement getNews executed");
			} else
				return null;
		} catch (SQLException sqle) {
			logger.error("The problem with the statement getNews", sqle);
		}
		return news;
	}

	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		String getUsers = "SELECT * FROM users";
		User user = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(getUsers);
			while (resultSet.next()) {
				user = new User(resultSet.getString("email"),
						resultSet.getString("password"),
						resultSet.getString("name"),
						resultSet.getString("surname"));
				users.add(user);
			}
			logger.info("Statement getUsers executed");
		} catch (SQLException sqle) {
			logger.error("The problem with the statement getUsers", sqle);
		}
		return users;
	}

	@Override
	public List<News> getListNews() {
		List<News> listNews = new ArrayList<News>();
		News news = null;
		String getListNews = "SELECT * FROM news";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(getListNews);
			while (resultSet.next()) {
				news = new News(resultSet.getInt("id"),
						resultSet.getString("title"),
						resultSet.getString("category"),
						resultSet.getString("annotation"),
						resultSet.getString("author"),
						resultSet.getString("date"),
						resultSet.getString("text"));
				listNews.add(news);
			}
			logger.info("Statement getListNews executed");
		} catch (SQLException sqle) {
			logger.error("The problem with the statement getListNews", sqle);
		}

		return listNews;
	}

	@Override
	public int addUser(User user) {
		String addUser = "INSERT INTO users VALUES(?,?,?,?)";
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(addUser);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getSurname());
			result = preparedStatement.executeUpdate();
			logger.info("Statement addUser executed");
		} catch (SQLException sqle) {
			logger.error("The problem with the statement addUser", sqle);
		}
		return result;
	}

	@Override
	public int addNews(News news) {
		String addNews = "INSERT INTO news VALUES(?,?,?,?,?,?,?)";
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(addNews);
			preparedStatement.setInt(1, news.getId());
			preparedStatement.setString(2, news.getTitle());
			preparedStatement.setString(3, news.getCategory());
			preparedStatement.setString(4, news.getAnnotation());
			preparedStatement.setString(5, news.getAuthor());
			preparedStatement.setString(6, news.getDate());
			preparedStatement.setString(7, news.getText());
			result = preparedStatement.executeUpdate();
			logger.info("Statement addNews executed");
		} catch (SQLException sqle) {
			logger.error("The problem with the statement addNews", sqle);
		}
		return result;
	}

	@Override
	public int deleteUser(String email) {
		String deleteUser = "DELETE FROM users WHERE email=?";
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(deleteUser);
			preparedStatement.setString(1, email);
			result = preparedStatement.executeUpdate();
			logger.info("Statement deleteUser executed");
		} catch (SQLException sqle) {
			logger.error("The problem with the statement deleteUser", sqle);
		}

		return result;
	}

	@Override
	public int deleteNews(int id) {
		String deleteNews = "DELETE FROM news WHERE id=?";
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(deleteNews);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeUpdate();
			logger.info("Statement deleteNews executed");
		} catch (SQLException sqle) {
			logger.error("The problem with the statement deleteNews", sqle);
		}
		return result;
	}

	@Override
	public int updateUser(String email, String password, String name,
			String surname) {
		String updateUser = "UPDATE users SET password = ?, name = ?, surname= ? WHERE email=?";
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(updateUser);
			preparedStatement.setString(4, email);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, surname);
			result = preparedStatement.executeUpdate();
			logger.info("Statement updateUser executed");
		} catch (SQLException sqle) {
			logger.error("The problem with the statement updateUser", sqle);
		}
		return result;
	}

	@Override
	public int updateNews(int id, String title, String category,
			String annotation, String author, String date, String text) {

		String updateNews = "UPDATE news SET title = ?,category = ?,annotation= ?,author = ?,date = ?, text = ? WHERE id=?";
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(updateNews);
			preparedStatement.setInt(7, id);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, category);
			preparedStatement.setString(3, annotation);
			preparedStatement.setString(4, author);
			preparedStatement.setString(5, date);
			preparedStatement.setString(6, text);
			result = preparedStatement.executeUpdate();
			logger.info("Statement updateNews executed");
		} catch (SQLException sqle) {
			logger.error("The problem with the statement updateNews", sqle);
		}
		return result;
	}

	@Override
	public List<News> getNewsOfCategory(String category) {
		List<News> listNews = new ArrayList<News>();
		News news = null;
		String getNewsOfCategory = "SELECT * FROM news WHERE category=\""
				+ category + "\"";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(getNewsOfCategory);
			while (resultSet.next()) {
				news = new News(resultSet.getInt("id"),
						resultSet.getString("title"),
						resultSet.getString("category"),
						resultSet.getString("annotation"),
						resultSet.getString("author"),
						resultSet.getString("date"),
						resultSet.getString("text"));
				listNews.add(news);
			}
			logger.info("Statement getNewsOfCategory executed");
		} catch (SQLException sqle) {
			logger.error("The problem with the statement getNewsOfCategory", sqle);
		}

		return listNews;
	}

	@Override
	public List<News> getNewsOfDate(String date) {
		List<News> listNews = new ArrayList<News>();
		News news = null;
		String getNewsOfCategory = "SELECT * FROM news WHERE date=\"" + date
				+ "\"";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(getNewsOfCategory);
			while (resultSet.next()) {
				news = new News(resultSet.getInt("id"),
						resultSet.getString("title"),
						resultSet.getString("category"),
						resultSet.getString("annotation"),
						resultSet.getString("author"),
						resultSet.getString("date"),
						resultSet.getString("text"));
				listNews.add(news);
			}
			logger.info("Statement getNewsOfDate executed");
		} catch (SQLException sqle) {
			logger.error("The problem with the statement getNewsOfDate", sqle);
		}
		return listNews;
	}

}
