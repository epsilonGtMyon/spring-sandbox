drop table if exists MY_TABLE;
drop table if exists MY_LOG;
drop table if exists MY_EXCEPTION;

create table MY_TABLE (
   ID             bigint auto_increment not null
  ,STRING_COL     varchar(100)
  ,BIGINT_COL     bigint
  ,INTEGER_COL    integer
  ,BIGDECIMAL_COL decimal(10, 2)
  ,DATE_COL       date
  ,TIMESTAMP_COL  timestamp
  ,CREATED_AT     timestamp
  ,UPDATED_AT     timestamp
  ,constraint PK_MY_TABLE primary key (
	ID
  )
);

create table MY_LOG (
   SEQ            bigint auto_increment not null
  ,LOG_MESSAGE    varchar(2000)
  ,CREATED_AT     timestamp
  ,UPDATED_AT     timestamp
  ,constraint PK_MY_LOG primary key(
	SEQ
  )
);

create table MY_EXCEPTION (
   EX_KEY         varchar(5)
  ,AMOUNT         decimal(10, 2)
  ,CREATED_AT     timestamp
  ,UPDATED_AT     timestamp
  ,constraint PK_MY_EXCEPTION primary key(
	EX_KEY
  )
);