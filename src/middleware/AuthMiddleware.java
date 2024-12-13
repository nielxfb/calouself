package middleware;

import util.SessionManager;

public class AuthMiddleware {
	
	public static Boolean loggedIn() {
		SessionManager session = SessionManager.getInstance();
		return session.getUser() != null;
	}

	public static String getRole() {
		SessionManager session = SessionManager.getInstance();
		return session.getUser().getRole();
	}

}
