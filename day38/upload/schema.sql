drop database if exists mymedia;

create database mymedia;

use mymedia;

create table pictures (
   pic_id char(8) not null,
   content mediumblob not null,
   mime varchar(128),
   
   constraint primary key(pic_id)
);

grant all privileges on mymedia.* to 'fred'@'%';

flush privileges;