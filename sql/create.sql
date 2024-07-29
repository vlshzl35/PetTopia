create user 'pettopia-db'@'%' identified by '@Pettopia06';

create database menudb;

grant all privileges on menudb.* to 'pettopia-db'@'%';

select user, host from mysql.user;

show grants for 'pettopia-db'@'%';