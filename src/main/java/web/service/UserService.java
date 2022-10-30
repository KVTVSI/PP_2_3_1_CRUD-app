package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    boolean addUser(User user);

    User getUser(String email);

    List<User> getAllUsers();

    void updateUser(String userEmail, User updatedUser);

    boolean deleteUser(String email);
}
