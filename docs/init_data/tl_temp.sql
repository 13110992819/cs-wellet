
#增加系统参数
   ## 价格计算x
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('coin_price_x','eth_coin_price_x','10','admin',now(),'eth价格计算x','CD-COIN000017','CD-COIN000017');
    ## 代理人分成
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('fen_cheng_rule','agent_fen_cheng_fee','0.001','admin',now(),'代理人推荐分成比例','CD-COIN000017','CD-COIN000017');

#创建表用户关系表
CREATE TABLE `tstd_user_settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `type` varchar(4) NOT NULL COMMENT '设置类型',
  `value` varchar(10) NOT NULL COMMENT '预留值',
  `create_datetime` datetime NOT NULL,
  `update_datetime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_type` (`user_id`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#修改用户关系表
ALTER TABLE `tstd_user_relation`
ADD COLUMN `type` VARCHAR(4) NULL COMMENT '关系类型' AFTER 'to_user';
  #原关系都是信任
  UPDATE `tstd_user_relation` SET `type` = '1';

#用户代理人
