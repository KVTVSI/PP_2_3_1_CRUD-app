package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDao {

    boolean addUser(User user);

    User getUser(String email);

    List<User> getAllUsers();

    void updateUser(String userEmail, User updatedUser);

    boolean deleteUser(String email);


}
