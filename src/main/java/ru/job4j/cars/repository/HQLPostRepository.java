package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLPostRepository implements PostRepository {

    private final CrudRepository crudRepository;

    @Override
    public Post create(Post post) {
        crudRepository.run(session -> session.save(post));
        return post;
    }

    @Override
    public void update(Post post) {
        crudRepository.run(session -> session.update(post));
    }

    @Override
    public void delete(int id) {
        crudRepository.run("DELETE Post p WHERE p.id = :fId", Map.of("fId", id));
    }

    @Override
    public Optional<Post> findById(int id) {
        return crudRepository.optional("from Post p WHERE p.id = :fId",
                Post.class, Map.of("fId", id));
    }
}
