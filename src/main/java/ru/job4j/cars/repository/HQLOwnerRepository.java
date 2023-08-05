package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Owner;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLOwnerRepository implements OwnerRepository {

    private final CrudRepository crudRepository;

    @Override
    public Owner create(Owner owner) {
        crudRepository.run(session -> session.save(owner));
        return owner;
    }

    @Override
    public void update(Owner owner) {
        crudRepository.run(session -> session.update(owner));
    }

    @Override
    public void delete(int id) {
        crudRepository.run("DELETE Owner o WHERE o.id = :fId", Map.of("fId", id));
    }

    @Override
    public Optional<Owner> findById(int id) {
        return crudRepository.optional("FROM Owner o WHERE o.id = :fId",
                Owner.class, Map.of("fId", id));
    }
}
