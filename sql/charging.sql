create table td_userinfo
(
    id          varchar(50)  default ''                   not null
        primary key,
    username    varchar(50)  default ''                   not null comment '用户名',
    email       varchar(50)  default ''                   not null,
    password    varchar(255) default ''                   not null comment '密码',
    token       varchar(200) default ''                   not null,
    status      int          default 0                    not null comment '0 未激活 1 正常 2 黑名单用户',
    vehicle_id  varchar(50)  default ''                   not null comment '车辆 id',
    create_time timestamp(3) default CURRENT_TIMESTAMP(3) not null,
    update_time timestamp(3) default CURRENT_TIMESTAMP(3) not null on update CURRENT_TIMESTAMP(3)
)
    comment '用户信息';
create table if not exists charging.vehicles
(
    id            varchar(50)    not null,
    car_name      varchar(50)    not null,
    kwh           decimal(10, 8) not null,
    charging_port varchar(50)    not null
    );

INSERT INTO charging.vehicles (id, car_name, kwh, charging_port)
VALUES
    ('1', 'Tesla Model Y', 75.00000000, 'Type 2'),
    ('2', 'Hyundai Ioniq 5', 58.00000000, 'Type 2'),
    ('3', 'Kia EV6', 77.40000000, 'Type 2'),
    ('4', 'Volkswagen ID.4', 77.00000000, 'Type 2'),
    ('5', 'BMW i4', 80.70000000, 'Type 2'),
    ('6', 'Nissan Leaf', 62.00000000, 'CHAdeMO, Type 2');
