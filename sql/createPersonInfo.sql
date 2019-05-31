use o2o;
create table `tb_person_info`
(
    `user_id`        int(10) not null auto_increment,
    `name`           varchar(32)      default null,
    `profile_name`   varchar(1024)    default null,
    `gender`         varchar(2)       default null,
    `email`          varchar(30)      default null,
    `enable_status`  int(2)  not null default '0' comment '0:禁止使用本商城,1:允许使用本商城',
    `user_type`      int(2)  not null default '1' comment '1:顾客,2:店家,3:超级管理员',
    `create_time`    datetime         default null,
    `last_edit_time` datetime         default null,
    primary key (user_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  Default CHARSET = utf8;