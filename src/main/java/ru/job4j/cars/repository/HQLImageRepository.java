package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Image;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLImageRepository implements ImageRepository {

    private final CrudRepository crudRepository;

    @Override
    public Image create(Image image) {
        crudRepository.run(session -> session.save(image));
        return image;
    }

    @Override
    public void delete(int id) {
        crudRepository.run("DELETE Image i WHERE i.id = :fId", Map.of("fId", id));
    }

    @Override
    public Optional<Image> findById(int id) {
        return crudRepository.optional("FROM Image i WHERE i.id = :fId",
                Image.class, Map.of("fId", id));
    }
}
