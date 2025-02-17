create table if not exists TX_TOKEN (
   CLIENT_UNIQUE_ID    varchar(300) not null
  ,WINDOW_ID           varchar(300) not null
  ,SESSION_ID          varchar(300) not null
  ,TOKEN_VALUE         varchar(300) not null
  ,CREATED_AT          timestamp    not null
  ,constraint PK_TX_TOKEN primary key(
     CLIENT_UNIQUE_ID
    ,WINDOW_ID
  )
)
;