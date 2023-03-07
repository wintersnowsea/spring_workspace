--mysql
--use jspshop/1234

create table member(
	member_idx int primary key auto_increment
	, id varchar(20)
	, pass varchar(20)
	, name varchar(20)
	, email varchar(50)
	, addr1 text
	, addr2 text
) default character set utf8;

--추가하기
alter table member
add regdate timestamp default now();

--pass 암호화를 위해 varchar 변경
alter table member
modify column pass varchar(64);

