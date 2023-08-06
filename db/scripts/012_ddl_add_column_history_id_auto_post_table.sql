alter table auto_post add column
    history_id int not null references history_post(id);