DROP TABLE A;
-- 사용자 기본 정보
CREATE TABLE IF NOT EXISTS A(
	a_num int primary key auto_increment,
	name VARCHAR(50)
);

DROP TABLE IF EXISTS B;
-- 추가 정보
CREATE TABLE IF NOT EXISTS B(
	b_num int primary key auto_increment,
	a_num int ,
	age int,
	addr VARCHAR(50),
	phone VARCHAR(50),
	FOREIGN KEY(a_num) REFERENCES A(a_num)	
);
-- 선호 직업
CREATE TABLE IF NOT EXISTS C(
	c_num int,
	a_num int 
);
-- 선호 지역
DROP TABLE IF EXISTS D;
CREATE TABLE IF NOT EXISTS D(
	d_num int primary key auto_increment,
	a_num int,
	addr VARCHAR(50)
);

INSERT INTO A(name) VALUES('최기근');
INSERT INTO A(name) VALUES('박금삼');
INSERT INTO A(name) VALUES('박동현');
INSERT INTO A(name) VALUES('양영근');
INSERT INTO A(name) VALUES('김하늘');

INSERT INTO B(a_num,age,addr,phone) VALUES(1,19,'부산','01011111111');
INSERT INTO B(a_num,age,addr,phone) VALUES(2,25,'서울','01022222222');
INSERT INTO B(a_num,age,addr,phone) VALUES(3,35,'경기','01033333333');
INSERT INTO B(a_num,age,addr,phone) VALUES(4,27,'서울','01044444444');
INSERT INTO B(a_num,age,addr,phone) VALUES(5,35,'부산','01055555555');

INSERT INTO C VALUES(101,1);
INSERT INTO C VALUES(201,1);
INSERT INTO C VALUES(101,2);
INSERT INTO C VALUES(101,3);
INSERT INTO C VALUES(101,4);
INSERT INTO C VALUES(201,4);
INSERT INTO C VALUES(101,5);
INSERT INTO C VALUES(201,5);

INSERT INTO D(a_num,addr) VALUES(1,'부산');
SELECT * FROM C;

SELECT * FROM A INNER JOIN B ON A.a_num = B.a_num
WHERE A.a_num IN(SELECT a_num FROM C WHERE c_num = 201)
AND A.a_num IN(SELECT a_num FROM C WHERE c_num = 101)
AND A.a_num IN(SELECT a_num FROM D WHERE addr LIKE '부산')
ORDER BY A.a_num DESC limit 0 , 10;

 