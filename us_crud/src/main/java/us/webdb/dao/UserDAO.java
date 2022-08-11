package us.webdb.dao;

import us.webdb.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    void addUser(User user);
    void removeUserById(long id);
    User getUserById(long id);
    void updateUser(User user);
}
