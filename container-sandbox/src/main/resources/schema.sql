drop all objects;

create table EMP (
   EMP_ID       varchar(7)
  ,FIRST_NAME   varchar(50)
  ,FAMILY_NAME  varchar(50)
  ,CREATED_AT   timestamp not null
  ,UPDATED_AT   timestamp not null
  ,constraint PK_EMP primary key(
    EMP_ID
  )
)
;
