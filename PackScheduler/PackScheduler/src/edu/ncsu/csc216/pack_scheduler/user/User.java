package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Creates a user object and allows modification and setting of name, id, email and more.
 * @author musamalik
 * @author Sachi Vyas
 *
 */
public abstract class User {
	/** first name of user */
	private String firstName;
	/** last name of user */
	private String lastName;
	/** id of user */
	private String id;
	/** email of user */
	private String email;
	/** password of user */
	private String password;

	/**
	 * Constructor for User object
	 * @param firstName first name of user
	 * @param lastName last name of user
	 * @param id id of user
	 * @param email email of user
	 * @param password password of user
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);		
	}

	/**
	 * Returns user's first name
	 * @return user's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets user's first name 
	 * @param firstName the first name to set
	 * @throws IllegalArgumentException if the first name is invalid
	 */
	public void setFirstName(String firstName) {
		if ("".equals(firstName) || firstName == null) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Returns user's last name
	 * @return user's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * sets user's last name
	 * @param lastName the last name to set
	 * @throws IllegalArgumentException if the last name is invalid
	 */
	public void setLastName(String lastName) {
		if ("".equals(lastName) || lastName == null) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Returns user's id
	 * @return user's id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets user's id
	 * @param id the id to set
	 * @throws IllegalArgumentException if the id is invalid
	 */
	public void setId(String id) {
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returns user's email 
	 * @return user's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets user's email
	 * @param email the email to set
	 * @throws IllegalArgumentException if the email is null or empty string
	 * @throws IllegalArgumentException if the email does not contain "@"
	 * @throws IllegalArgumentException if the email does not contain "."
	 * @throws IllegalArgumentException if the last index of "." in the email is less than the index of "@"
	 */
	public void setEmail(String email) {
		if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!email.contains("@")) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (email.lastIndexOf(".") < email.indexOf("@")) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Returns user's password
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets user's password
	 * @param password the password to set
	 * @throws IllegalArgumentException if the password is invalid
	 */
	public void setPassword(String password) {
		if (password == null || "".equals(password)) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	/**
	 * Returns hash code of user object
	 * @return hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Returns if objects are equal
	 * @return whether or not objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
}
