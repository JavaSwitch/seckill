--创建数据库
create database seckill;

--使用数据库
use seckill;

--创建用户基础信息表
create table user_base(
`id` bigint not null auto_increment comment '用户id',
`name` varchar(255) not null comment '用户名',
`phone` bigint not null comment '手机号',
`address` varchar(255) not null comment '收货地址',
`create_time` timestamp not null default current_timestamp comment '创建时间',
`update_time` timestamp not null default current_timestamp comment '变更时间',
primary key (id)
)engine=InnoDB auto_increment=1000 default charset=utf8 comment '用户基础信息表';

--创建秒杀商品订单表
create table seckill_order(
`order_id` bigint not null auto_increment comment '订单id',
`user_id` bigint not null comment '用户id',
`goods_id` bigint not null comment '秒杀商品id',
`state` int not null comment '订单状态,-1表示未成功，0表示待支付，1表示成功',
`create_time` timestamp not null default current_timestamp comment '创建时间',
`update_time` timestamp not null default current_timestamp comment '变更时间',
primary key (order_id),
unique key idx_user_id_goods_id (user_id,goods_id),
key idx_create_time (create_time)
)engine=InnoDB default charset=utf8 comment '秒杀商品订单表';

--创建秒杀商品表
create table seckill_goods(
`goods_id` bigint not null auto_increment comment '商品id',
`name` varchar(255) not null comment '商品名称',
`store_number` int not null comment '库存数量',
`start_time` timestamp not null default current_timestamp comment '秒杀开始时间',
`end_time` timestamp not null default current_timestamp comment '秒杀结束时间',
`create_time` timestamp default current_timestamp not null comment '创建时间',
primary key (goods_id),
key idx_start_time (start_time),
key idx_end_time (end_time)
)engine=InnoDB auto_increment=1000 default charset=utf8 comment '秒杀商品表';


--初始化数据
insert into seckill_goods(name,store_number,start_time,end_time)
values 
    ('1000元秒杀iphone6s',100,'2020-02-20 00:00:00','2020-02-21 00:00:00'),
    ('1000元秒杀小米10',100,'2020-02-20 00:00:00','2020-02-21 00:00:00'),
    ('1000元秒杀华为 Mate30',100,'2020-02-20 00:00:00','2020-02-21 00:00:00'),
    ('5000元秒杀Macbook Pro',100,'2020-02-20 00:00:00','2020-02-21 00:00:00');
