package crud.dao;

import crud.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void addUser(User user) {
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void deleteUser(Integer id) {
        entityManager.remove(userById(id));
    }

    @Transactional
    @Override
    public List<User> getUsers() {
        List<User> users = entityManager.createQuery("From User").getResultList();
        return users;
    }

    @Transactional
    @Override
    public User userById(Integer id) {
        return entityManager.find(User.class, id);
    }
}
