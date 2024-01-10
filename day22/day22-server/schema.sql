-- Drop database if exists
drop database if exists contacts;

create database contacts;

use contacts;

create table bff (

   email varchar(128) not null,
   name varchar(128) not null,
   dob date,
   phone char(8) default '12345678',
   comments text,
   last_update timestamp 
      default current_timestamp 
      on update current_timestamp,

   constraint pk_email primary key(email)
);

create table bff_status (

   status_id int auto_increment,
   email varchar(128) not null,
   status_timestamp timestamp default current_timestamp,
   bff_status enum('good', 'bad', 'indifferent') default 'indifferent',

   -- primary key(status_id)
   constraint pk_status_id primary key(status_id)
);