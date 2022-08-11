package us.webdb.service;

import us.webdb.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsersFromDatabase();
    void addUserToDatabase(User user);
    void removeUserByIdFromDatabase(long id);
    User getUserByIdFromDatabase(long id);
    void updateUserInDatabase(User user);
}
