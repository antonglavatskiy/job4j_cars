package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLEngineRepository implements EngineRepository {

    private final CrudRepository crudRepository;

    @Override
    public Engine create(Engine engine) {
        crudRepository.run(session -> session.save(engine));
        return engine;
    }

    @Override
    public void update(Engine engine) {
        crudRepository.run(session -> session.update(engine));
    }

    @Override
    public void delete(int id) {
        crudRepository.run("DELETE Engine e WHERE e.id = :fId", Map.of("fId", id));
    }

    @Override
    public Optional<Engine> findById(int id) {
        return crudRepository.optional("FROM Engine e WHERE p.id = :fId",
                Engine.class, Map.of("fId", id));
    }
}
