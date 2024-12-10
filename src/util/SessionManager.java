package util;

import model.User;

public class SessionManager {

	private User user;
	
	private SessionManager() {
		user = null;
	}
	
	private static SessionManager instance;
	
	public static SessionManager getInstance() {
		return instance = (instance == null) ? new SessionManager() : instance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void logout() {
		user = null;
	}

}
