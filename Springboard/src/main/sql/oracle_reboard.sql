conn jsp/1234

--table 조회
select table_name from user_tables;

--reboard 생성
 create table reboard(
 	reboard_idx number primary key
 	, title varchar2(100)
 	, writer varchar2(30)
 	, content clob
 	, regdate date default sysdate
 	, hit number default 0
 	, team number default 0
 	, step number default 0
 	, depth number default 0
 )
 
 create sequence seq_reboard
 increment by 1
 start with 1
 
 --자리확보
 
