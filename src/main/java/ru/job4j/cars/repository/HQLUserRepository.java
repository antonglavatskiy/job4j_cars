package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;


public class HQLUserRepository implements UserRepository {

    private final SessionFactory sf;

    public HQLUserRepository(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public User create(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return user;
    }

    @Override
    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE User u SET u.login = :fLogin, u.password = :fPassword WHERE u.id = :fId")
                    .setParameter("fLogin", user.getLogin())
                    .setParameter("fPassword", user.getPassword())
                    .setParameter("fId", user.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE User u WHERE u.id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        Query<User> query = session.createQuery(
                "from User u ORDER BY u.id", User.class);
        return query.list();
    }

    @Override
    public Optional<User> findById(int id) {
        Session session = sf.openSession();
        Query<User> query = session.createQuery(
                "from User u WHERE u.id = :fId", User.class);
        query.setParameter("fId", id);
        return Optional.ofNullable(query.uniqueResult());
    }

    @Override
    public List<User> findByLikeLogin(String key) {
        StringBuilder find = new StringBuilder("%")
                .append(key)
                .append("%");
        Session session = sf.openSession();
        Query<User> query = session.createQuery(
                "from User u WHERE u.login LIKE :fKey", User.class);
        query.setParameter("fKey", find.toString());
        return query.list();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        Query<User> query = session.createQuery(
                "from User u WHERE u.login = :fLogin", User.class);
        query.setParameter("fLogin", login);
        return Optional.ofNullable(query.uniqueResult());
    }
}
