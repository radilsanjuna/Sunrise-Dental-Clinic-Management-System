package dao;

import db.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // 1. Existing Login Method
    public User login(String username, String password) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    // Check if you have full_name in your DB. If not, you can remove this line:
                    user.setFullName(rs.getString("full_name")); 
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return user;
    }

    // 2. Add New User
    public boolean addUser(User user) {
        String sql = "INSERT INTO users (full_name, username, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            return false;
        }
    }

    // 3. Update Existing User
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET full_name = ?, username = ?, password = ?, role = ? WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setInt(5, user.getUserId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
            return false;
        }
    }

    // 4. Delete User
    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

    // 5. Get All Users (For the JTable)
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return userList;
    }
    
    // Add this inside UserDAO.java
public User getUserById(int userId) {
    User user = null;
    String sql = "SELECT * FROM users WHERE user_id = ?";
    
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, userId);
        
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        }
    } catch (SQLException e) {
        System.out.println("Error finding user: " + e.getMessage());
    }
    
    return user;
}

// Dentist Profile එකක් නැති, User Role එක "Dentist" වෙන Users ලාව විතරක් අරන් එනවා
public List<User> getUnlinkedDentistUsers() {
    List<User> list = new ArrayList<>();
    String sql = "SELECT u.user_id, u.full_name, u.username FROM users u " +
                 "LEFT JOIN dentists d ON u.user_id = d.user_id " +
                 "WHERE u.role = 'Dentist' AND d.user_id IS NULL";
                 
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setFullName(rs.getString("full_name"));
            user.setUsername(rs.getString("username"));
            list.add(user);
        }
    } catch (SQLException e) {
        System.out.println("Error fetching unlinked users: " + e.getMessage());
    }
    return list;
}



public List<User> getDentistUsers() {

    List<User> userList = new ArrayList<>();

    String sql = "SELECT * FROM users "
            + "WHERE role = 'Dentist'";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {

            User user = new User();

            user.setUserId(
                    rs.getInt("user_id")
            );

            user.setFullName(
                    rs.getString("full_name")
            );

            user.setUsername(
                    rs.getString("username")
            );

            user.setPassword(
                    rs.getString("password")
            );

            user.setRole(
                    rs.getString("role")
            );

            userList.add(user);
        }

    } catch (SQLException e) {

        System.out.println("Error loading dentist users: "
                + e.getMessage());
    }

    return userList;
}



}