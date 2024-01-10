drop database if exists todos;

create database todos;

use todos;

create table tasks (
	task_id int auto_increment,
	title varchar(256) not null,
	due_date date,
	priority tinyint,
	completed boolean default false,

	primary key(task_id)
);

grant all privileges on todos.* to fred@'%';
flush privileges;

insert into tasks(title, due_date, priority) values
	('Task 1', '2024-01-15', 3),
	('Task 2', '2024-01-16', 4),
	('Task 3', '2024-01-12', 2)
	;
