create user 'pettopia-db'@'%' identified by '@Pettopia06';

create database pettopiadb;

grant all privileges on `pettopiadb`.* to 'pettopia-db'@'%';

select user, host from mysql.user;

show grants for 'pettopia-db'@'%';