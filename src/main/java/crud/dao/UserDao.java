package crud.dao;

import crud.entity.User;
import java.util.List;

public interface UserDao {
    void addUser(User user);
    void deleteUser(Integer id);
    List<User> getUsers();
    User userById(Integer id);
}
