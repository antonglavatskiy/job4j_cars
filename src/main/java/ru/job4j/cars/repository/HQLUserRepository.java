package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLUserRepository implements UserRepository {

    private final CrudRepository crudRepository;

    @Override
    public User create(User user) {
        crudRepository.run(session -> session.save(user));
        return user;
    }

    @Override
    public void update(User user) {
        crudRepository.run(session -> session.update(user));
    }

    @Override
    public void delete(int id) {
        crudRepository.run("DELETE User u WHERE u.id = :fId", Map.of("fId", id));
    }

    @Override
    public List<User> findAllOrderById() {
        return crudRepository.query("from User u ORDER BY u.id", User.class);
    }

    @Override
    public Optional<User> findById(int id) {
        return crudRepository.optional("from User u WHERE u.id = :fId",
                User.class, Map.of("fId", id));
    }

    @Override
    public List<User> findByLikeLogin(String key) {
        return crudRepository.query("from User u WHERE u.login LIKE :fKey",
                User.class, Map.of("fKey", "%" + key + "%"));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return crudRepository.optional("from User u WHERE u.login = :fLogin",
                User.class, Map.of("fLogin", login));
    }
}
