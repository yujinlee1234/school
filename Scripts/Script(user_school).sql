-- �б�
DROP SCHEMA IF EXISTS school;

-- �б�
CREATE SCHEMA school;

-- �л�
CREATE TABLE school.student (
	stud_id INTEGER     NOT NULL COMMENT '�й�', -- �й�
	name    VARCHAR(50) NOT NULL COMMENT '����', -- ����
	email   VARCHAR(50) NOT NULL COMMENT '����', -- ����
	dob     DATE        NULL     COMMENT '����' -- ����
)
COMMENT '�л�';

-- �л�
ALTER TABLE school.student
	ADD CONSTRAINT PK_student -- �л� �⺻Ű
		PRIMARY KEY (
			stud_id -- �й�
		);
		
-- Create School DB-----------------------------------------------------
		
insert into student values(1,'����','ksb@test.co.kr','1970-1-1');
insert into student values (2,'������','dkb@test.co.kr','2000-12-12'),(3,'�����','kky@test.co.kr','1999-12-06');

select * from student;

select stud_id as studid, name, email, dob from student where stud_id=2;

update student set name='����', email='ky@test.co.kr', dob='1977-09-07' where stud_id=2;

delete from student where stud_id=3;










