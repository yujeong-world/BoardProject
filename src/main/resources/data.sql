--사용자
insert into user_account(user_id, pw, name, email, created_by, created_at, modified_by, modified_at) values ('yujeong', '1234', '권유정','yujeong@email.com','yujeong', '2023-07-19 14:05:10', 'yujeong', '2023-07-19 14:08:10');
insert into user_account(user_id, pw, name, email, created_by, created_at, modified_by, modified_at) values ('jeongho', '1234', '김정호','jeongho@email.com','jeongho', '2023-07-19 14:05:10', 'jeongho', '2023-07-19 14:08:10');



insert into board(user_id, title, content, created_by, created_at, modified_by, modified_at)
values ('yujeong', '햄스터 키우고 싶어요', '안녕하세요. 고양이와 햄스터 둘 다 동시에 키울 수 있나요?', 'yujeong', '2023-07-19 14:05:10', 'yujeong', '2023-07-19 14:08:10');
insert into board(user_id, title, content, created_by, created_at, modified_by, modified_at)
values ('jeongho', '햄문가의 햄버거 추천글 ', '안녕하세요. 햄버거 추천해드릴까요?', 'jeongho', '2023-07-19 14:05:10', 'yujeong', '2023-07-19 14:08:10');
insert into board(user_id, title, content, created_by, created_at, modified_by, modified_at)
values ('yujeong', '맛집 추천 리스트', '안녕하세요. 맛집을 추천해드릴게요', 'yujeong', '2023-07-18 14:05:10', 'yujeong', '2023-07-18 14:08:10');
insert into board(user_id, title, content, created_by, created_at, modified_by, modified_at)
values ('jeongho', '가지 볶음밥 레시피', '안녕하세요. 가지볶음밥 레시피입니다.', 'jeongho', '2023-07-16 14:05:10', 'jeongho', '2023-07-16 14:08:10');

insert into board(user_id, title, content, created_by, created_at, modified_by, modified_at)
values ('yujeong', '스프링 부트 학습 중', '스프링 부트 공부중입니다.', 'yujeong', '2023-06-19 14:05:10', 'yujeong', '2023-06-19 14:08:10');
insert into board(user_id, title, content, created_by, created_at, modified_by, modified_at)
values ('jeongho', '이번주 주말에 뭐하지', '주말에 뭐할까요', 'jeongho', '2023-07-18 14:05:10', 'yujeong', '2023-07-18 14:08:10');
insert into board(user_id, title, content, created_by, created_at, modified_by, modified_at)
values ('yujeong', '정보처리 기사합격률', '정보처리 기사 합격률에 대해 알려드립니다', 'yujeong', '2023-07-18 15:05:10', 'yujeong', '2023-07-18 15:08:10');
insert into board(user_id, title, content, created_by, created_at, modified_by, modified_at)
values ('jeongho', '로제 파스타 레시피', '안녕하세요. 로제 파스타 레시피입니다.', 'jeongho', '2023-07-17 14:05:10', 'jeongho', '2023-07-17 14:08:10');

insert into board(user_id, title, content, created_by, created_at, modified_by, modified_at)
values ('yujeong', '메로나 먹고 싶다.', '메로나 먹고 싶다', 'yujeong', '2023-07-19 14:05:10', 'yujeong', '2023-07-19 14:08:10');
insert into board(user_id, title, content, created_by, created_at, modified_by, modified_at)
values ('jeongho', '떡볶이 추천글 ', '떡볶이 맛있다.', 'jeongho', '2023-07-19 14:05:10', 'yujeong', '2023-07-19 14:08:10');
insert into board(user_id, title, content, created_by, created_at, modified_by, modified_at)
values ('yujeong', '오늘 날씨', '오늘 날씨가 매우 더워요', 'yujeong', '2023-07-18 14:05:10', 'yujeong', '2023-07-18 14:08:10');
insert into board(user_id, title, content, created_by, created_at, modified_by, modified_at)
values ('jeongho', '홍수 피해 지역', '안녕하세요. 홍수 피해지역을 알려드립니다', 'jeongho', '2023-07-16 14:05:10', 'jeongho', '2023-07-16 14:08:10');

--댓글 데이터
insert into comment(board_id, user_id, content,created_by, created_at, modified_by, modified_at)
values ('1', 'jeongho', '아니요! 그런 생각을 하다니 당신은 싸이코 패스입니다.', 'jeongho', '2023-07-20 13:11', 'jeongho', '2023-07-20 13:11');
insert into comment(board_id, user_id, content,created_by, created_at, modified_by, modified_at)
values ('1', 'jeongho', '그럴 수는 없습니다.', 'jeongho', '2023-07-20 14:11', 'jeongho', '2023-07-20 14:11');