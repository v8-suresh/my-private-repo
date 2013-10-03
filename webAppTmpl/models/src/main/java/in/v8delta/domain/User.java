package in.v8delta.domain;

import in.v8delta.template.myWebAppTmpl.core.utils.AppConstants;

import java.io.Serializable;

/**
 * 
 * Domain Object of each User
 * 
 * @author v8-suresh
 * 
 */

public class User implements Serializable {

	private static final long serialVersionUID = 8032550450613762604L;

	private String username;
	private String email;
	private String password;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email
				+ ", password=" + password + "]";
	}

}