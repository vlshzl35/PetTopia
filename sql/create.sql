create user 'pettopia-db'@'%' identified by '@Pettopia06';

create database pettopiadb;

grant all privileges on `pettopiadb`.* to 'pettopia-db'@'%';

select user, host from mysql.user;

show grants for 'pettopia-db'@'%';

# 강의장 db 쿼리

create user 'pettopia-db'@'%' identified by 'pettopia-db';

create database pettopiadb_test;

grant all privileges on `pettopiadb`.* to 'pettopia-db'@'%';

select user, host from mysql.user;

show grants for 'pettopia-db'@'%';