package web.dao;

import org.apache.commons.validator.EmailValidator;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public boolean existsUser(String email) {
        return entityManager.createQuery("from User where email = :email", User.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findAny()
                .orElse(null) != null;
    }

    public boolean emailIsValid(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    @Override
    public boolean addUser(User user) {
        if (!existsUser(user.getEmail()) &&
                user.getName().length() != 0 &&
                user.getEmail().length() != 0 &&
                user.getLastName().length() != 0 &&
                emailIsValid(user.getEmail())) {
            entityManager.persist(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getUser(String email) {
        if (existsUser(email)) {
            return entityManager.createQuery("from User where email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } else {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public void updateUser(String userEmail, User updatedUser) {
        if (!existsUser(updatedUser.getEmail()) && updatedUser.getEmail().length() != 0 && emailIsValid(updatedUser.getEmail())) {
            User userToBeUpdated = getUser(userEmail);

            userToBeUpdated.setEmail(updatedUser.getEmail());
            userToBeUpdated.setName(updatedUser.getName());
            userToBeUpdated.setLastName(updatedUser.getLastName());
        }
    }


    @Override
    public boolean deleteUser(String email) {
        if (existsUser(email)) {
            entityManager.remove(getUser(email));
            return true;
        } else {
            return false;
        }
    }


}