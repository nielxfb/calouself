package model;

import util.Connect;

import java.sql.ResultSet;
import java.util.UUID;

public class User {

    private String userId;
    private String username;
    private String password;
    private String phoneNumber;
    private String address;
    private String role;

    public User(String userId, String username, String password, String phoneNumber, String address, String role) {
        this.userId = userId != null ? userId : UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    public static User find(String username) {
        String query = "SELECT * FROM users WHERE username = '" + username + "'";
        Connect db = Connect.getConnection();
        try (ResultSet rs = db.executeQuery(query)) {
            if (rs.next()) {
                return new User(rs.getString("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("phone_number"), rs.getString("address"), rs.getString("role"));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public void create() {
        String query = "INSERT INTO users (user_id, username, password, phone_number, address, role) VALUES ('" + userId + "', '" + username + "', '" + password + "', '" + phoneNumber + "', '" + address + "', '" + role + "')";
        Connect db = Connect.getConnection();
        db.executeUpdate(query);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
