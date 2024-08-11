create table td_userinfo
(
    id          varchar(50)  default ''                   not null
        primary key,
    username    varchar(50)  default ''                   not null comment '用户名',
    email       varchar(50)  default ''                   not null,
    password    varchar(255) default ''                   not null comment '密码',
    token       varchar(200) default ''                   not null,
    status      int          default 0                    not null comment '0 未激活 1 正常 2 黑名单用户',
    create_time timestamp(3) default CURRENT_TIMESTAMP(3) not null,
    update_time timestamp(3) default CURRENT_TIMESTAMP(3) not null on update CURRENT_TIMESTAMP(3)
)
    comment '用户信息';
