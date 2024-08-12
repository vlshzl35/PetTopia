-- 리뷰 1
INSERT INTO tbl_ent_review (review_id, ent_id, member_id, rating, review_content, created_at, updated_at, business_registration, ent_name, payment_date, total_price, receipt_img_url)
VALUES (1, 1, 1, 'FIVE', '이 업체는 정말 훌륭합니다! 서비스도 좋고, 분위기도 좋습니다.', '2024-08-01 14:30:00', '2024-08-02 09:00:00', '123-45-67890', '마이펫동물의료센터', '2024-07-30', 15000, 'https://navercloud.com/receipt1.jpg');

-- 리뷰 2
INSERT INTO tbl_ent_review (review_id, ent_id, member_id, rating, review_content, created_at, updated_at, business_registration, ent_name, payment_date, total_price, receipt_img_url)
VALUES (2, 2, 2, 'THREE', '경험은 좋았지만, 대기 시간이 길었습니다.', '2024-08-03 16:45:00', '2024-08-04 11:20:00', '234-56-78901', '청담우리동물병원', '2024-08-02', 20000, 'https://navercloud.com/receipt2.jpg');

-- 리뷰 3
INSERT INTO tbl_ent_review (review_id, ent_id, member_id, rating, review_content, created_at, updated_at, business_registration, ent_name, payment_date, total_price, receipt_img_url)
VALUES (3, 3, 3, 'ONE', '서비스가 매우 부족했습니다. 개선이 필요합니다.', '2024-08-05 09:00:00', '2024-08-05 09:00:00', '345-67-89012', '닥터펫동물의료센터', '2024-08-04', 10000, 'https://navercloud.com/receipt3.jpg');
