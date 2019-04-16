CREATE DATABASE `ds0` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `ds0`.`t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `price` decimal(20,2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB;



/*  ds1  */

CREATE DATABASE `ds1` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `ds1`.`t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `price` decimal(20,2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB;



