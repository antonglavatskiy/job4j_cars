package ru.job4j.cars.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ImageDTO {
    @EqualsAndHashCode.Include
    private String name;

    private byte[] content;

    @EqualsAndHashCode.Include
    private int postId;
}
