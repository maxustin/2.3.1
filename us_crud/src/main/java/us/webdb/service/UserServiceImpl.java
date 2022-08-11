package us.webdb.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.webdb.dao.UserDAO;
import us.webdb.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<User> getAllUsersFromDatabase() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void addUserToDatabase(User user) {
        userDAO.addUser(user);
    }

    @Override
    @Transactional
    public void removeUserByIdFromDatabase(long id) {
        userDAO.removeUserById(id);
    }

    @Override
    @Transactional
    public User getUserByIdFromDatabase(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void updateUserInDatabase(User user) {
        userDAO.updateUser(user);
    }
}
