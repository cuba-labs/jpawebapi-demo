create table REFAPP_TEST_ENTITY (
    ID uuid not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    F_NAME varchar(255),
    L_NAME varchar(255),
    AGE integer,
    primary key (ID)
)^