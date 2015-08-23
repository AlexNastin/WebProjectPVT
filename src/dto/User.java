package dto;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8247806357887134379L;
	private String email;
	private String password;
	private String name;
	private String surname;

	public User(String email, String password, String name, String surname) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
	}

	public User() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User user = (User) obj;
			if (email.equals(user.email)
					&& (password.equals(user.password) && name
							.equals(user.name)) && surname.equals(user.surname)) {
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
		result = prime * result + ((email == "") ? 0 : email.hashCode());
		result = prime * result + ((name == "") ? 0 : name.hashCode());
		result = prime * result + ((password == "") ? 0 : password.hashCode());
		result = prime * result + ((surname == "") ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", name="
				+ name + ", surname=" + surname + "]";
	}

}
