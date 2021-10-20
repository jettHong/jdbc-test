create schema my_jdbc;

use my_jdbc;

create table account
(
    id      char(36) primary key,
    card_id varchar(20) unique,
    name    varchar(8) not null,
    money   float(10, 2) default 0
);
-- 未指明引擎则默认使用 InnoDB

insert into account
values ('6ab71673-9502-44ba-8db0-7f625f17a67d', '1234567890', '张三', 1000);
insert into account (id, card_id, name)
values ('9883a53d-9127-4a9a-bdcb-96cf87afe831', '0987654321', '张三');


create table account_myisam
(
    id      char(36) primary key,
    card_id varchar(20) unique,
    name    varchar(8) not null,
    money   float(10, 2) default 0
) engine = MyISAM;
-- 指明使用 MyISAM 引擎。

insert into account_myisam
values ('6ab71673-9502-44ba-8db0-7f625f17a67d', '1234567890', '张三', 1000);
insert into account_myisam (id, card_id, name)
values ('9883a53d-9127-4a9a-bdcb-96cf87afe831', '0987654321', '张三');
