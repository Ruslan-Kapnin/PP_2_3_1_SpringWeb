package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.dao.UserDAO;
import web.model.User;

import java.util.List;


@Component
public class UserService {
    @Autowired
    private UserDAO userDao;

    public void save(User user) {
        userDao.save(user);
    }

    public void update(Long id, User user) {
        userDao.update(id, user);
    }

    public void remove(Long id) {
        userDao.remove(id);
    }

    public User getById(Long id) {
        return userDao.getById(id);
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }
}
