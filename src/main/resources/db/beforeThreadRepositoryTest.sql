#create our test table

CREATE TABLE `threads_test` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `dt_created` datetime DEFAULT NULL,
  `dt_updated` datetime DEFAULT NULL,
  `customer_user_id` int(10) unsigned DEFAULT '0',
  `support_user_id` int(10) unsigned DEFAULT '0' COMMENT '0=no support joined yet',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;