create user 'pettopia-db'@'%' identified by '@Pettopia06';

create database pettopiadb;

grant all privileges on `pettopiadb`.* to 'sh'@'%';

select user, host from mysql.user;

show grants for 'sh'@'%';