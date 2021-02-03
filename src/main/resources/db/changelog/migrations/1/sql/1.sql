create table film
(
    id       bigint not null
        primary key auto_increment,
    name text   not null
);
create table user
(
    id       bigint not null
        primary key auto_increment,
    name text   not null
);
create table review
(
    id bigint not null auto_increment,
    user_id bigint not null,
    film_id bigint not null,
    rating int not null,
    created_at date not null,
    primary key (id) ,
    foreign key (user_id) REFERENCES user(id),
    foreign key (film_id) REFERENCES film(id)
);



