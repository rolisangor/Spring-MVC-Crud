package crud.service;

import crud.dao.UserDao;
import crud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImp {

    @Autowired
    private UserDao userDao;

    public void addUser(User user){
        userDao.addUser(user);
    }

    public List<User> listAll() {
        return userDao.getUsers();
    }

    public User get(Integer id) {
        return userDao.userById(id);
    }

    public void delete(Integer id) {
        userDao.deleteUser(id);
    }
}
