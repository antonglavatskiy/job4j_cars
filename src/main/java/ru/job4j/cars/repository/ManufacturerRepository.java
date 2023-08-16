package ru.job4j.cars.repository;

import ru.job4j.cars.model.Manufacturer;

import java.util.Optional;

public interface ManufacturerRepository {
    Manufacturer create(Manufacturer manufacturer);
    void update(Manufacturer manufacturer);
    void delete(int id);
    Optional<Manufacturer> findById(int id);
}
