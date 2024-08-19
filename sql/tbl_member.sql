# INSERT INTO tbl_member (birth, created_at, id, address, email, name, nick_name, password, phone, profile_image, gender, sitter_status)
# VALUES
#        ('1992-11-11', '2024-08-13', 2, '부산광역시 해운대구', 'lee02@example.com', '이영희', '영희짱', 'password123', '010-2345-6789', 'profile2.jpg', 'F', 'PENDING'),
#        ('1985-02-14', '2024-08-14', 3, '대구광역시 수성구', 'park03@example.com', '박민수', '민수킹', 'password123', '010-3456-7890', 'profile3.jpg', 'M', 'NONE'),
#        ('1998-07-30', '2024-08-15', 4, '인천광역시 남동구', 'choi04@example.com', '최지현', '지현러브', 'password123', '010-4567-8901', 'profile4.jpg', 'F', 'APPROVED'),
#        ('2000-01-01', '2024-08-16', 5, '광주광역시 서구', 'jung05@example.com', '정하늘', '하늘천사', 'password123', '010-5678-9012', 'profile5.jpg', 'M', 'PENDING'),
#        ('1994-03-12', '2024-08-17', 6, '대전광역시 중구', 'cho06@example.com', '조수미', '수미맘', 'password123', '010-6789-0123', 'profile6.jpg', 'F', 'NONE'),
#        ('1988-09-09', '2024-08-18', 7, '울산광역시 남구', 'ahn07@example.com', '안성기', '성기쵝오', 'password123', '010-7890-1234', 'profile7.jpg', 'M', 'APPROVED'),
#        ('1996-06-06', '2024-08-19', 8, '세종특별자치시', 'jang08@example.com', '장미란', '미란공주', 'password123', '010-8901-2345', 'profile8.jpg', 'F', 'PENDING'),
#        ('1993-12-25', '2024-08-20', 9, '경기도 성남시 분당구', 'yoon09@example.com', '윤준호', '준호짱짱', 'password123', '010-9012-3456', 'profile9.jpg', 'M', 'NONE'),
#        ('1997-10-10', '2024-08-21', 10, '경기도 수원시 팔달구', 'kang10@example.com', '강수지', '수지천사', 'password123', '010-0123-4567', 'profile10.jpg', 'F', 'APPROVED');

INSERT INTO pettopiadb.tbl_member (id, name, nick_name, email, password, phone, address, birth, created_at, profile_image, sitter_status)
VALUES
    (1, '김철수', '철수짱', 'lee02@example.com', 'password123', '010-2345-6789', '부산광역시 해운대구', '1992-11-11', '2024-08-13', 'profile2.jpg', 'PENDING'),
    (2, '이영희', '영희짱', 'lee02@example.com', 'password123', '010-2345-6789', '부산광역시 해운대구', '1992-11-11', '2024-08-13', 'profile2.jpg', 'PENDING'),
    (3, '박민수', '민수킹', 'park03@example.com', 'password123', '010-3456-7890', '대구광역시 수성구', '1985-02-14', '2024-08-14', 'profile3.jpg', 'NONE'),
    (4, '최지현', '지현러브', 'choi04@example.com', 'password123', '010-4567-8901', '인천광역시 남동구', '1998-07-30', '2024-08-15', 'profile4.jpg', 'APPROVED'),
    (5, '정하늘', '하늘천사', 'jung05@example.com', 'password123', '010-5678-9012', '광주광역시 서구', '2000-01-01', '2024-08-16', 'profile5.jpg', 'PENDING'),
    (6, '조수미', '수미맘', 'cho06@example.com', 'password123', '010-6789-0123', '대전광역시 중구', '1994-03-12', '2024-08-17', 'profile6.jpg', 'NONE'),
    (7, '안성기', '성기쵝오', 'ahn07@example.com', 'password123', '010-7890-1234', '울산광역시 남구', '1988-09-09', '2024-08-18', 'profile7.jpg', 'APPROVED'),
    (8, '장미란', '미란공주', 'jang08@example.com', 'password123', '010-8901-2345', '세종특별자치시', '1996-06-06', '2024-08-19', 'profile8.jpg', 'PENDING'),
    (9, '윤준호', '준호짱짱', 'yoon09@example.com', 'password123', '010-9012-3456', '경기도 성남시 분당구', '1993-12-25', '2024-08-20', 'profile9.jpg', 'NONE'),
    (10, '강수지', '수지천사', 'kang10@example.com', 'password123', '010-0123-4567', '경기도 수원시 팔달구', '1997-10-10', '2024-08-21', 'profile10.jpg', 'APPROVED');
