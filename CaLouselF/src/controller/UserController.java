package controller;

import abstraction.Response;
import builder.ResponseBuilder;
import model.User;

public class UserController {

    public static Response<User> login(String username, String password) {
        User user = User.find(username);
        if (user == null) {
            return new ResponseBuilder<User>(false).withMessage("User not found").build();
        }

        if (!user.getPassword().equals(password)) {
            return new ResponseBuilder<User>(false).withMessage("Invalid credentials").build();
        }

        return new ResponseBuilder<User>(true).withData(user).build();
    }

    public static Response<User> register(String username, String password, String phoneNumber, String address, String role) {
        Response<User> response = checkAccountValidation(username, password, phoneNumber, address, role);
        if (!response.isSuccess) {
            return response;
        }

        return new ResponseBuilder<User>(true).withData(response.data).build();
    }

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

        User user = new User(username, password, phoneNumber, address, role);
        user.create();

        return new ResponseBuilder<User>(true).withData(user).build();
    }

    public static Boolean exists(String username) {
        return User.find(username) != null;
    }

    public static Boolean passwordValid(String password) {
        for (char c : password.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean phoneNumberValid(String phoneNumber) {
        return phoneNumber.startsWith("+62") && phoneNumber.length() >= 12;
    }

}
