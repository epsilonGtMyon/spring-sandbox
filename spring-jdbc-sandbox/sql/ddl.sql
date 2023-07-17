-- ====================================
-- TABLE
-- ====================================

-- EMP
create table EMP (
   EMP_ID      varchar(6)  not null
  ,FIRST_NAME  varchar(10) not null
  ,FAMILY_NAME varchar(10) not null
  ,BLOOD_TYPE  varchar(2)  not null
  ,NOTE        varchar(1000)
  ,CREATED_AT  timestamp   not null
  ,UPDATED_AT  timestamp   not null
  ,constraint PK_EMP primary key(
     EMP_ID
  )
)
;

comment on table  EMP  is '社員';
comment on column EMP.EMP_ID  is '社員ID';
