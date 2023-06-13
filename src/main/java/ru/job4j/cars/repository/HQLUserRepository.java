package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cars.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class HQLUserRepository implements UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(HQLUserRepository.class.getName());

    private final SessionFactory sf;

    public HQLUserRepository(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public User create(User user) {
        Transaction transaction = null;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Пользователь не сохранен", e);
        }
        return user;
    }

    @Override
    public void update(User user) {
        Transaction transaction = null;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE User u SET u.login = :fLogin, u.password = :fPassword WHERE u.id = :fId")
                    .setParameter("fLogin", user.getLogin())
                    .setParameter("fPassword", user.getPassword())
                    .setParameter("fId", user.getId())
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Пользователь не обновлен", e);
        }
    }

    @Override
    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery(
                            "DELETE User u WHERE u.id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Пользователь не удален", e);
        }
    }

    @Override
    public List<User> findAllOrderById() {
        Transaction transaction = null;
        List<User> rsl = new ArrayList<>();
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery(
                    "from User u ORDER BY u.id", User.class);
            rsl = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Пользователи не найдены", e);
        }
        return rsl;
    }

    @Override
    public Optional<User> findById(int id) {
        Transaction transaction = null;
        Optional<User> rsl = Optional.empty();
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery(
                    "from User u WHERE u.id = :fId", User.class)
                    .setParameter("fId", id);
            rsl = query.uniqueResultOptional();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Пользователь не найден", e);
        }
        return rsl;
    }

    @Override
    public List<User> findByLikeLogin(String key) {
        StringBuilder find = new StringBuilder("%")
                .append(key)
                .append("%");
        Transaction transaction = null;
        List<User> rsl = new ArrayList<>();
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery(
                    "from User u WHERE u.login LIKE :fKey", User.class)
                    .setParameter("fKey", find.toString());
            rsl = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Пользователи не найдены", e);
        }
        return rsl;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        Transaction transaction = null;
        Optional<User> rsl = Optional.empty();
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery(
                    "from User u WHERE u.login = :fLogin", User.class)
                    .setParameter("fLogin", login);
            rsl = query.uniqueResultOptional();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Пользователь не найден", e);
        }
        return rsl;
    }
}
