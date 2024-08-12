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


# 로컬 데이터베이스 생성

create database pettopiadb;

# 데이터베이스 권한 부여

grant all privileges on pettopiadb.* to 'sh'@'%';

# 부여된 권한 보기

show grants for 'sh'@'%';