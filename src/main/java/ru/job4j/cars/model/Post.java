package ru.job4j.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "auto_post")
public class Post {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private LocalDateTime created = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_history_id")
    private List<PriceHistory> histories = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "participates",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<Image> images = new ArrayList<>();
}
