ust jspshop

create table gallery(
	gallery_idx int primary key auto_increment
	, title varchar(100)
	, writer varchar(30)
	, content text
	, regdate timestamp default now()
	, hit int default 0
	, filename varchar(30)
) default character set utf8;