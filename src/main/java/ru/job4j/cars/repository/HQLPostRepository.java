package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.List;
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
        return crudRepository.optional("FROM Post p WHERE p.id = :fId",
                Post.class, Map.of("fId", id));
    }

    @Override
    public List<Post> findPostsLastDay() {
        return crudRepository.query("""
                FROM Post p WHERE p.created >= :fCreated
                """, Post.class,
                Map.of("fCreated", LocalDateTime.now().minusDays(1)));
    }

    @Override
    public List<Post> findPostsWithImage() {
        return crudRepository.query("""
                      FROM Post p
                      WHERE size (p.images) > 0
                      ORDER BY p.created
                      """, Post.class);
    }

    @Override
    public List<Post> findPostsByManufacturerName(String name) {
        return crudRepository.query("""
                FROM Post p WHERE p.car.manufacturer.name = :fName
                ORDER BY p.created
                """,
                Post.class, Map.of("fName", name));
    }
}
