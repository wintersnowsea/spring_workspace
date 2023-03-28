--mysql
create database jspshop; --계정 생성

--관리자 table생성
create table admin(
	admin_idx int primary key auto_increment
	, admin_id varchar(20)
	, admin_pass varchar(20)
	, admin_name varchar(20)
) default character set utf8;

insert into admin(admin_id, admin_pass, admin_name)
values('zino', '1234', '지노');

--상품관리 관련 table 생성
--부모테이블
create table category(
	category_idx int primary key auto_increment
	, category_name varchar(30)
) default character set utf8;

insert into category(category_name) values('상의');
insert into category(category_name) values('하의');
insert into category(category_name) values('액세서리');
insert into category(category_name) values('신발');
insert into category(category_name) values('가방');

--category테이블의 자식테이블-상품
create table product(
	product_idx int primary key auto_increment
	, category_idx int
	, product_name varchar(30)
	, brand varchar(100)
	, price int default 0
	, discount int default 0
	, detail text --이미지 포함한 태그
	, constraint fk_category_product foreign key(category_idx)
		references category(category_idx)
) default character set utf8;

--product테이블의 자식테이블-이미지
create table pimg(
	pimg_idx int primary key auto_increment
	, product_idx int
	, filename varchar(25)
	, constraint fk_product_pimg foreign key(product_idx)
		references product(product_idx)
) default character set utf8;

--product테이블의 자식테이블-색상
create table color(
	color_idx int primary key auto_increment
	, product_idx int
	, color_name varchar(20)
	, constraint fk_product_color foreign key(product_idx)
		references product(product_idx)
) default character set utf8;

--product테이블의 자식테이블-사이즈
create table psize(
	psize_idx int primary key auto_increment
	, product_idx int
	, psize_name varchar(20)
	, constraint fk_product_psize foreign key(product_idx)
		references product(product_idx)
) default character set utf8;

--product테이블의 자식테이블-리뷰
create table review(
	review_idx int primary key auto_increment
	, product_idx int
	, writer varchar(30)
	, regdate timestamp default now()
	, content text
	, score int default 0
	, constraint fk_product_review foreign key(product_idx)
		references product(product_idx)
) default character set utf8;

------------------------------------
--장바구니 테이블 만들기
create table cart(
	cart_idx int primary key auto_increment
	, member_idx int
	, product_idx int
	, ea int default 0
	, constraint fk_product_cart foreign key(product_idx) references product(product_idx)
) default character set utf8;

------------------------------------
--장바구니 다시 만들기
drop table cart;

--장바구니 테이블
create table cart(
	cart_idx int primary key auto_increment
	, member_idx int
	, product_idx int
	, ea int default 0
	, constraint fk_product_cart foreign key(product_idx) references product(product_idx)
	, constraint fk_member_cart foreign key(member_idx) references member(member_idx)
) default character set utf8;