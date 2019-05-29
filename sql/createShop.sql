use o2o;
create table `tb_shop`
(
    `shop_id`          int(10)      not null auto_increment,
    `shop_name`        varchar(255) not null,
    `shop_Desc`        varchar(1023)         default null,
    `shop_Addr`        varchar(200)          default null,
    `phone`            varchar(128)          default null,
    `shop_img`         varchar(1023)         default null,
    `priority`         int(3)                default '0',
    `enable_status`    int(2)       not null default '0',
    `create_time`      DATETIME              default null,
    `last_edit_time`   datetime              default null,
    `shop_category_id` int(11)               default null,
    `owner_id`         int(10)      not null comment '店铺创建人',
    `area_id`          int(5)                default null,
    `advice`           varchar(255)          default null,
    PRIMARY KEY (shop_id),
    constraint `fk_shop_category` foreign key (`shop_category_id`) references `tb_shop_category` (`shop_category_id`),
    constraint `fk_shop_profile` foreign key (`owner_id`) references `tb_person_info` (`user_id`),
    constraint `fk_shop_area` foreign key (`area_id`) references `tb_area` (`area_id`)

) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT charset = utf8;