package controller;

import dao.UserDAO;
import model.User;
import java.util.List;

public class UserController {
    
    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    // 1. Login Authentication
    public User login(String username, String password) {
        return userDAO.login(username, password);
    }

    // 2. Add New User
    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }

    // 3. Update Existing User
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    // 4. Delete User by ID
    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }

    // 5. Get All Users (Used to load the JTable)
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
    
// Add this inside UserController.java
public User getUserById(int userId) {
    return userDAO.getUserById(userId);
}

public List<User> getUnlinkedDentistUsers() {
    return userDAO.getUnlinkedDentistUsers();
}

public List<User> getDentistUsers() {
    return userDAO.getDentistUsers();
}


}