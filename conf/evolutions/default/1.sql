# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table person (
  id                        bigint not null,
  ean                       bigint,
  firstname                 varchar(255),
  lastname                  varchar(255),
  email                     varchar(255),
  number                    varchar(255),
  notes                     varchar(255),
  constraint pk_person primary key (id))
;

create sequence person_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists person;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists person_seq;

