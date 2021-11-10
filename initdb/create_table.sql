CREATE USER filedemo IDENTIFIED BY WElcome##1234;
GRANT CONNECT TO filedemo;
GRANT RESOURCE TO filedemo;
GRANT UNLIMITED TABLESPACE TO filedemo;
GRANT CREATE TABLE TO filedemo;
GRANT CREATE VIEW TO filedemo;
GRANT CREATE ANY PROCEDURE TO filedemo; 
GRANT EXECUTE ANY PROCEDURE TO filedemo;
GRANT CREATE JOB TO filedemo;
GRANT MANAGE SCHEDULER TO filedemo;
GRANT SODA_APP TO filedemo;


/***  create table ***/
CREATE TABLE tb_documents (
  id number constraint tb_documents_pk primary key,
  name varchar(200),
  description varchar(400) ,
  filename varchar(400) ,
  content BLOB , 
  content_type varchar(255) ,
  created date
); 

/

CREATE TABLE TB_UITEST2 (
  id number constraint tb_TB_UITEST2_pk primary key,
  name varchar(200),
  fee number,
  flex BLOB , 
  created date,
  updated date,
  CONSTRAINT JSON_DOCUMENTS_JSON_CHK CHECK (flex IS JSON)
  );
/

create sequence SEQ_DOCUMENT_ID
minvalue 1  
maxvalue 9999999999  
start with 101  
increment by 1 
NOCYCLE
NOCACHE;
/

create sequence seq_tb_uitest2_id
minvalue 1  
maxvalue 9999999999  
start with 1  
increment by 1 
NOCYCLE
NOCACHE;
/