package us.webdb.service;

import org.springframework.stereotype.Service;
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
    public List<User> getAllUsersFromDatabase() {
        return userDAO.getAllUsers();
    }

    @Override
    public void addUserToDatabase(User user) {
        userDAO.addUser(user);
    }

    @Override
    public void removeUserByIdFromDatabase(long id) {
        userDAO.removeUserById(id);
    }

    @Override
    public User getUserByIdFromDatabase(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void updateUserInDatabase(User user) {
        userDAO.updateUser(user);
    }
}
