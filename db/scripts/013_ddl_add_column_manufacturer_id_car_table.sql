alter table car add column
    manufacturer_id int not null references manufacturer(id);