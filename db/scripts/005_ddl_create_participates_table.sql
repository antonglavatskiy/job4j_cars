create table participates
(
   id serial PRIMARY KEY,
   post_id int REFERENCES auto_post(id) not null,
   user_id int REFERENCES auto_user(id) not null
);