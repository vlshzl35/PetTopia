insert into pettopiadb.tbl_member (id, email, gender, name, password, phone, address, birth, created_at, nick_name, profile_image, sitter_status)
values  (1, 'c@c', 'M', '최창욱', '$2a$10$PG/EMdeDoRbQiEDVaxh2yeCZHVtnvYs1URfg1j77kMPXV40rFDFlq', 'c', null, '1992-02-24', '2024-08-11', '갓창욱', '', 'NONE'),
        (2, 'c1@c', 'M', '박태준', '$2a$10$zo/DtEmUpsjgWAhyi0EmAuZymJsSYHQipQQ4Go0uC/9vvO3nydZIq', '1', null, '1992-02-02', '2024-08-11', '희망이아부지', '', 'NONE'),
        (3, 'c2@c', 'F', '홍지민', '$2a$10$m4Z/hst2kLWUkPgrzZVWe.vwaM2vXlF5EJ647Qmg/PGQKZKyvUwLO', '1', null, '0123-12-03', '2024-08-11', '호찌민', '', 'NONE'),
        (4, 'c3@c', 'F', '양희윤', '$2a$10$Pr5CqFuFaeu1TW9r0jgl7OMvoiZSzdOE7d3.GxLvrDkPpbWJGTBoK', 'c', null, '1223-02-02', '2024-08-11', '양양', '', 'NONE'),
        (5, 'c4@c', 'F', '이영우', '$2a$10$jmtvwsHKcQgWe2X1uho23OhQM.dd0J54LMHzLKq0lom0e4bGpcdWi', 'c', null, '1232-02-02', '2024-08-11', '인간악기', '', 'NONE'),
        (6, 'c5@c', 'M', '유재석', '$2a$10$lUYjU9nXqDY2hefWukGm0uAqLJQVEcJXaTq0GJJOnMa4ZDRnwyGdq', 'c', null, '3421-02-03', '2024-08-11', 'c', '', 'NONE'),
        (7, 'c6@c', 'M', '박명수', '$2a$10$XIrmmwY.n0xNvgwE5BLC4up2Rb9KMvOAti9BCOzA.NYZVSRGrgtOe', 'c', null, '2312-12-02', '2024-08-11', 'c', '', 'NONE'),
        (8, 'c7@c', 'M', '노홍철', '$2a$10$4kFKgtY/6FCL9pMZhOrvlu3s1n7HF2GFdmIK/dTt2Lylv8Qf3G2ge', 'c', null, '1223-02-02', '2024-08-11', 'c', '', 'NONE'),
        (9, 'c9@c', 'M', '하하', '$2a$10$.kCbn64VEoxHBc22IKDd1eqJH3X0sIqplNdvwUHSMV5blnFfanzQW', 'c', null, '2132-02-02', '2024-08-11', 'c', '', 'NONE'),
        (10, 'c10@c', 'F', '정수정', '$2a$10$DsjluOHs.9SG6fIiRFgHNeiGz6kmakIMqrqg8uVurGBVO68SCWIGi', 'c', null, '1222-02-03', '2024-08-11', 'c', '', 'NONE'),
        (11, 'c11@c', 'F', '정수연', '$2a$10$N6t/RWATMTka0/C71fu23uoelkWQ9mITeE1RYG7yOOJgJ6VlVdbpy', 'c', null, '1232-02-02', '2024-08-11', 'c', '', 'NONE');

INSERT INTO tbl_member (birth, created_at, id, address, email, name, nick_name, password, phone, profile_image, gender, sitter_status)
VALUES
       ('1992-11-11', '2024-08-13', 2, '부산광역시 해운대구', 'lee02@example.com', '이영희', '영희짱', 'password123', '010-2345-6789', 'profile2.jpg', 'F', 'PENDING'),
       ('1985-02-14', '2024-08-14', 3, '대구광역시 수성구', 'park03@example.com', '박민수', '민수킹', 'password123', '010-3456-7890', 'profile3.jpg', 'M', 'NONE'),
       ('1998-07-30', '2024-08-15', 4, '인천광역시 남동구', 'choi04@example.com', '최지현', '지현러브', 'password123', '010-4567-8901', 'profile4.jpg', 'F', 'APPROVED'),
       ('2000-01-01', '2024-08-16', 5, '광주광역시 서구', 'jung05@example.com', '정하늘', '하늘천사', 'password123', '010-5678-9012', 'profile5.jpg', 'M', 'PENDING'),
       ('1994-03-12', '2024-08-17', 6, '대전광역시 중구', 'cho06@example.com', '조수미', '수미맘', 'password123', '010-6789-0123', 'profile6.jpg', 'F', 'NONE'),
       ('1988-09-09', '2024-08-18', 7, '울산광역시 남구', 'ahn07@example.com', '안성기', '성기쵝오', 'password123', '010-7890-1234', 'profile7.jpg', 'M', 'APPROVED'),
       ('1996-06-06', '2024-08-19', 8, '세종특별자치시', 'jang08@example.com', '장미란', '미란공주', 'password123', '010-8901-2345', 'profile8.jpg', 'F', 'PENDING'),
       ('1993-12-25', '2024-08-20', 9, '경기도 성남시 분당구', 'yoon09@example.com', '윤준호', '준호짱짱', 'password123', '010-9012-3456', 'profile9.jpg', 'M', 'NONE'),
       ('1997-10-10', '2024-08-21', 10, '경기도 수원시 팔달구', 'kang10@example.com', '강수지', '수지천사', 'password123', '010-0123-4567', 'profile10.jpg', 'F', 'APPROVED');