package ru.job4j.cars.repository;

import ru.job4j.cars.model.Image;

import java.util.Optional;

public interface ImageRepository {
    Image create(Image image);
    void delete(int id);
    Optional<Image> findById(int id);
}
