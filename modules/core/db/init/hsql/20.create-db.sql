-- begin REF_CAR
alter table REF_CAR add constraint FK_REF_CAR_ON_COLOUR foreign key (COLOUR_ID) references REF_COLOUR(ID)^
alter table REF_CAR add constraint FK_REF_CAR_ON_CURRENCY_CODE foreign key (CURRENCY_CODE) references REF_CURRENCY(CODE)^
alter table REF_CAR add constraint FK_REF_CAR_ON_CATEGORY foreign key (CATEGORY_ID) references SYS_CATEGORY(ID)^
create index IDX_REF_CAR_ON_COLOUR on REF_CAR (COLOUR_ID)^
create index IDX_REF_CAR_ON_CURRENCY_CODE on REF_CAR (CURRENCY_CODE)^
create index IDX_REF_CAR_ON_CATEGORY on REF_CAR (CATEGORY_ID)^
-- end REF_CAR
