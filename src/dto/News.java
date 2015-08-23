package dto;

import java.io.Serializable;

public class News implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 939505966334877066L;
	private int id;
	private String title;
	private String category;
	private String annotation;
	private String author;
	private String date;
	private String text;

	public News(int id, String title, String category, String annotation,
			String author, String date, String text) {
		this.id = id;
		this.title = title;
		this.category = category;
		this.annotation = annotation;
		this.author = author;
		this.date = date;
		this.text = text;
	}

	public News() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof News) {
			News news = (News) obj;
			if ((id == news.id) && (title.equals(news.title))
					&& (category.equals(news.category))
					&& (annotation.equals(news.annotation))
					&& (author.equals(news.author)) && (date.equals(news.date))
					&& text.equals(news.text)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((annotation == "") ? 0 : annotation.hashCode());
		result = prime * result + ((author == "") ? 0 : author.hashCode());
		result = prime * result + ((category == "") ? 0 : category.hashCode());
		result = prime * result + ((date == "") ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((text == "") ? 0 : text.hashCode());
		result = prime * result + ((title == "") ? 0 : title.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", category=" + category
				+ ", annotation=" + annotation + ", author=" + author
				+ ", date=" + date + ", text=" + text + "]";
	}

}
