drop table if exists events;
drop table if exists files;
drop table if exists users;

create table events
(
    id         bigint       not null auto_increment,
    event_type varchar(50) not null default 'CREATED',
    timestamp  datetime(6),
    file_id    bigint       not null,
    user_id    bigint       not null,
    primary key (id)
);

create table files
(
    id        bigint       not null auto_increment,
    file_path varchar(255) not null,
    file_name varchar(255) not null,
    primary key (id)
);

create table users
(
    id          bigint      not null auto_increment,
    user_name   varchar(25) not null default 'USER',
    user_status varchar(50) not null,
    primary key (id)
);

alter table events
    add constraint FK_file_id foreign key (file_id) references files (id);
alter table events
    add constraint FK_user_id foreign key (user_id) references users (id);
