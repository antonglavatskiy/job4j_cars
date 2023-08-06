create table history_owner(
    id serial primary key,
    startAt timestamp without time zone,
    endAt timestamp without time zone,
    owner_id int not null references owner(id),
    car_id int not null references car(id)
);