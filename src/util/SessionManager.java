package util;

import model.User;

/**
 * Utility class for managing user sessions.
 */
public class SessionManager {

    private User user;

    /**
     * Private constructor to initialize the session with no user.
     */
    private SessionManager() {
        user = null;
    }

    private static SessionManager instance;

    /**
     * Provides a synchronized instance of the SessionManager class.
     *
     * @return a singleton instance of the SessionManager class
     */
    public static SessionManager getInstance() {
        return instance = (instance == null) ? new SessionManager() : instance;
    }

    /**
     * Gets the current user in the session.
     *
     * @return the current user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user for the session.
     *
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Logs out the current user by setting the user to null.
     */
    public void logout() {
        user = null;
    }

}