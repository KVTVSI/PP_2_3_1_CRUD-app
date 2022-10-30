package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    @Transactional
    public User getUser(String email) {
        return userDao.getUser(email);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void updateUser(String userEmail, User updatedUser) {
        userDao.updateUser(userEmail, updatedUser);
    }

    @Override
    @Transactional
    public boolean deleteUser(String email) {
        return userDao.deleteUser(email);
    }
}
