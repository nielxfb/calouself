package controller;

import abstraction.Response;
import builder.ResponseBuilder;
import model.User;

/**
 * Controller class for handling user-related operations such as login and registration.
 */
public class UserController {

    /**
     * Handles user login.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return a Response object containing the login result and user data if successful
     */
    public static Response<User> login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return new ResponseBuilder<User>(false).withMessage("All fields are required!").build();
        }

        User user;
        try {
            user = User.getByUsername(username);
            if (user == null) {
                return new ResponseBuilder<User>(false).withMessage("User not found").build();
            }

            if (!user.getPassword().equals(password)) {
                return new ResponseBuilder<User>(false).withMessage("Invalid credentials").build();
            }

        } catch (Exception e) {
            return new ResponseBuilder<User>(false).withMessage("Invalid credentials").build();
        }

        return new ResponseBuilder<User>(true).withData(user).build();
    }

    /**
     * Handles user registration.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @param phoneNumber the phone number of the user
     * @param address the address of the user
     * @param role the role of the user (either "Seller" or "Buyer")
     * @return a Response object containing the registration result and user data if successful
     */
    public static Response<User> register(String username, String password, String phoneNumber, String address, String role) {
        Response<User> response = checkAccountValidation(username, password, phoneNumber, address, role);
        if (!response.isSuccess) {
            return response;
        }

        return new ResponseBuilder<User>(true).withData(response.data).build();
    }

    /**
     * Validates user account details.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @param phoneNumber the phone number of the user
     * @param address the address of the user
     * @param role the role of the user (either "Seller" or "Buyer")
     * @return a Response object containing the validation result
     */
    public static Response<User> checkAccountValidation(String username, String password, String phoneNumber, String address, String role) {
        String error = "";
        if (username.isEmpty() || password.isEmpty() || address.isEmpty() || (!role.equals("Seller") && !role.equals("Buyer"))) {
            error = "All fields are required";
        } else if (username.length() < 3) {
            error = "Username must be at least 3 characters";
        } else if (password.length() < 8) {
            error = "Password must be at least 8 characters";
        } else if (!phoneNumberValid(phoneNumber)) {
            error = "Invalid phone number";
        } else if (exists(username)) {
            error = "Username already exists";
        } else if (!passwordValid(password)) {
            error = "Password must contain at least one special character";
        }

        if (!error.isEmpty()) {
            return new ResponseBuilder<User>(false).withMessage(error).build();
        }

        User user = new User("", username, password, phoneNumber, address, role);
        user.create();

        return new ResponseBuilder<User>(true).withData(user).build();
    }

    /**
     * Checks if a user with the given username already exists.
     *
     * @param username the username to check
     * @return true if the user exists, false otherwise
     */
    public static Boolean exists(String username) {
        return User.getByUsername(username) != null;
    }

    /**
     * Validates the password.
     *
     * @param password the password to validate
     * @return true if the password contains at least one special character, false otherwise
     */
    public static Boolean passwordValid(String password) {
        for (char c : password.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates the phone number.
     *
     * @param phoneNumber the phone number to validate
     * @return true if the phone number is valid, false otherwise
     */
    public static Boolean phoneNumberValid(String phoneNumber) {
        return phoneNumber.startsWith("+62") && phoneNumber.length() >= 12;
    }

}