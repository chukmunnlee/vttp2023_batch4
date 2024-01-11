drop database if exists emart;

create database emart;

use emart;

create table purchase_order (
   po_id char(8) not null,
   created_on timestamp default current_timestamp,
   name varchar(64) not null,
   address varchar(256) not null,
   last_update timestamp default current_timestamp on update current_timestamp,

   primary key(po_id)
);

create table line_item (
   id int auto_increment, 
   item varchar(32) not null,
   quantity int default '1',
   po_id char(8) not null,

   primary key(id),
   constraint fk_po_id foreign key(po_id) references purchase_order(po_id)
);

grant all privileges on emart.* to fred@'%';

flush privileges;