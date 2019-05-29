use o2o;
create table `tb_shop_category`
(
    `shop_category_id`   int(10)      not null auto_increment,
    `shop_category_name` varchar(100) not null default '',
    `shop_category_desc` varchar(1000)         default '',
    `shop_category_img`  varchar(2000)         default null,
    `priority`           int(2)                default null,
    `create_time`        datetime              DEFAULT null,
    `last_edit_time`     datetime              default null,
    `parent_id`          int(10)               default null,
    primary key (`shop_category_id`),
    constraint `fk_shop_category_self` foreign key (`parent_id`) references `tb_shop_category` (`shop_category_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;
