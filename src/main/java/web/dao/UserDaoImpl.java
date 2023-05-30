package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;

@Component
@Transactional
public class UserDaoImpl implements UserDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    @Transactional
    public void update(Long id, User user) {
        entityManager.getTransaction().begin();

        User userToUpdate = getById(id);
        userToUpdate.setName(user.getName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setAge(user.getAge());

        entityManager.merge(userToUpdate);
        entityManager.getTransaction().commit();
    }

    @Override
    public void remove(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(getById(id));
        entityManager.getTransaction().commit();
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @PostConstruct
    private void init() {
        save(new User("Walter", "White", 52));
        save(new User("Jesse", "Pinkman", 25));
        save(new User("Saul", "Goodman", 32));
        save(new User("Mike", "Ehrmantraut", 69));
    }
}
