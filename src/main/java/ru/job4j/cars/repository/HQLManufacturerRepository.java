package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Manufacturer;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLManufacturerRepository implements ManufacturerRepository {

    private final CrudRepository crudRepository;

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        crudRepository.run(session -> session.save(manufacturer));
        return manufacturer;
    }

    @Override
    public void update(Manufacturer manufacturer) {
        crudRepository.run(session -> session.update(manufacturer));
    }

    @Override
    public void delete(int id) {
        crudRepository.run("DELETE Manufacturer m WHERE m.id = :fId", Map.of("fId", id));
    }

    @Override
    public Optional<Manufacturer> findById(int id) {
        return crudRepository.optional("FROM Manufacturer m WHERE m.id = :fId",
                Manufacturer.class, Map.of("fId", id));
    }
}
