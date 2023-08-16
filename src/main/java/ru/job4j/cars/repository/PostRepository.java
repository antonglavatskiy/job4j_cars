package ru.job4j.cars.repository;

import ru.job4j.cars.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post create(Post post);
    void update(Post post);
    void delete(int id);
    Optional<Post> findById(int id);
    List<Post> findPostsLastDay();
    List<Post> findPostsWithImage();
    List<Post> findPostsByManufacturerName(String name);
}
