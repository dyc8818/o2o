use o2o;
create table `tb_wechat_auth`
(
    `wechat_auth_id` int(10)       not null auto_increment,
    `user_id`        int(10)       not null,
    `open_id`        varchar(1024) not null,
    `create_time`    datetime default null,
    primary key (`wechat_auth_id`),
    constraint `fk_wechatauth_profile` foreign key (`user_id`) references `tb_person_info` (`user_id`)
) ENGINE = InnoDB
  auto_increment = 1
  DEFAULT CHARSET = utf8;
create table `tb_local_auth`
(
    `local_auth_id`  int(10)      not null auto_increment,
    `username`       varchar(128) not null,
    `password`       varchar(128) not null,
    `user_id`        int(10)      not null,
    `create_time`    datetime default null,
    `last_edit_time` datetime default null,
    primary key (`local_auth_id`),
    unique key uk_local_profile (`username`),
    constraint `fk_localauth_profile` foreign key (`user_id`) references `tb_person_info` (`user_id`)
) ENGINE = InnoDB
  auto_increment = 1
  DEFAULT CHARSET = utf8;
alter table tb_wechat_auth add unique index (open_id);