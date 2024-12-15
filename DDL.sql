create table users (
    user_id varchar(50),
    username varchar(50),
    password varchar(50),
    phone_number varchar(50),
    address varchar(50),
    role varchar(10),
    primary key (user_id)
);

create table items (
    item_id varchar(50),
    item_name varchar(50),
    item_size varchar(50),
    item_price int,
    item_category varchar(50),
    item_status varchar(50),
    seller_id varchar(50),
    primary key(item_id),
    foreign key (seller_id) references users (user_id)
);

create table wishlists (
    wishlist_id varchar(50),
    user_id varchar(50),
    item_id varchar(50),
    primary key (wishlist_id),
    foreign key user_id references users (user_id),
    foreign key item_id references items (item_id)
);