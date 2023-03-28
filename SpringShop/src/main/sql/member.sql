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

--삭제 후 다시 만들기 (23/03/27)
drop table member;

create table sns(
	sns_idx int primary key auto_increment
	, sns_name varchar(20)
) default character set utf8;

insert into sns(sns_name) values('google');
insert into sns(sns_name) values('kakao');
insert into sns(sns_name) values('naver');
insert into sns(sns_name) values('homepage');

create table member(
	member_idx int primary key auto_increment
	, uid varchar(30)
	, nickname varchar(25)
	, sns_idx int
	, constraint fk_sns_member foreign key (sns_idx) references sns(sns_idx)
	, regdate timestamp default now()
) default character set utf8;

