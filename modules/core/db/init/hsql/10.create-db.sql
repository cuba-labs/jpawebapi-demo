-- begin REF_CAR
create table REF_CAR (
    ID varchar(36) not null,
    CATEGORY_ID varchar(36),
    VERSION integer,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    VIN varchar(255),
    COLOUR_ID varchar(36),
    CURRENCY_CODE varchar(255),
    --
    primary key (ID)
)^
-- end REF_CAR
-- begin REF_COLOUR
create table REF_COLOUR (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    DESCRIPTION varchar(255) not null,
    --
    primary key (ID)
)^
-- end REF_COLOUR
-- begin REF_CURRENCY
create table REF_CURRENCY (
    CODE varchar(255) not null,
    UUID varchar(36),
    VERSION integer,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    --
    primary key (CODE)
)^
-- end REF_CURRENCY
-- begin REFAPP_TEST_ENTITY
create table REFAPP_TEST_ENTITY (
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
-- end REFAPP_TEST_ENTITY
