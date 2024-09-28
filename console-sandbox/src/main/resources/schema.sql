create table TABLE_SRC (
   ID        number(10, 0)
  ,MESSAGE   varchar(100)
  ,constraint PK_TABLE_SRC primary key (
     ID
  )
);


create table TABLE_DEST (
   ID        number(10, 0)
  ,MESSAGE   varchar(100)
  ,constraint PK_TABLE_DEST primary key (
     ID
  )
);