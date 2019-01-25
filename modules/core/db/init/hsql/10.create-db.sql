-- begin JPADEMO_TEST_ENTITY
create table JPADEMO_TEST_ENTITY (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    F_NAME varchar(255),
    L_NAME varchar(255),
    AGE integer,
    --
    primary key (ID)
)^
-- end JPADEMO_TEST_ENTITY

create table JPADEMO_TEST_INNER_ENTITY (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PARAM varchar(255),
    --
    primary key (ID)
)^
