package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLCarRepository implements CarRepository {

    private final CrudRepository crudRepository;

    @Override
    public Car create(Car car) {
        crudRepository.run(session -> session.save(car));
        return car;
    }

    @Override
    public void update(Car car) {
        crudRepository.run(session -> session.update(car));
    }

    @Override
    public void delete(int id) {
        crudRepository.run("DELETE Car c WHERE c.id = :fId", Map.of("fId", id));
    }

    @Override
    public Optional<Car> findById(int id) {
        return crudRepository.optional("FROM Car c WHERE c.id = :fId",
                Car.class, Map.of("fId", id));
    }
}
