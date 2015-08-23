package dao;

import java.util.List;

import dto.News;
import dto.User;

public interface DAO {

	User getUser(String email);

	News getNews(int id);

	List<User> getUsers();

	List<News> getListNews();

	int addUser(User user);

	int addNews(News news);

	int deleteUser(String email);

	int deleteNews(int id);

	int updateUser(String email, String password, String name, String surname);

	int updateNews(int id, String title, String category, String annotation,
			String author, String date, String text);

	List<News> getNewsOfCategory(String category);

	List<News> getNewsOfDate(String date);

}
