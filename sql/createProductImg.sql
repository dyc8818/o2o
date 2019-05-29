use o2o;
create table `tb_product_img`
(
    `product_img_id` int(20)       not null auto_increment,
    `img_addr`       varchar(2000) not null,
    `img_desc`       varchar(2000)          default null,
    `product_id`     int(20)       not null default '0',
    `priority`       int(2)                 default '0',
    `create_time`    datetime               default null,
    primary key (`product_img_id`),
    constraint `fk_proimg_product` foreign key (`product_id`) references `tb_product` (`product_id`)

) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT charset = utf8;