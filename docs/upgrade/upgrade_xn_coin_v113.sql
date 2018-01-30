
/*新增设置推荐人按钮*/
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201801260053087396194','设置推荐人','2','setUserReferee','74','admin',now(),'','SM201711071721131644746','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','SM201801260053087396194','admin',now(),NULL,'CD-COIN000017');

/*交易手续费文字修改为交易广告费*/
UPDATE `tsys_dict` SET `dvalue`='交易广告费' WHERE `parent_key`='jour_biz_type_user' AND `dkey`='tradefee';
UPDATE `tsys_dict` SET `dvalue`='交易广告费' WHERE `parent_key`='jour_biz_type' AND `dkey`='tradefee';
UPDATE `tsys_dict` SET `dvalue`='交易广告费收入' WHERE `parent_key`='jour_biz_type_plat' AND `dkey`='tradefee';

/*配置值文字修改*/
UPDATE `tsys_config` SET `remark`='用户默认的交易广告费率，注册时使用' WHERE `ckey`='trade_fee_rate';
UPDATE `tsys_config` SET `remark`='活动期间交易广告费率（小于0表示关闭活动，大于等于0表示开启并全局统一使用该广告费率）' WHERE `ckey`='activity_trade_fee_rate';
UPDATE `tsys_config` SET `remark`='普通交易者默认推荐分佣比例，注册时使用' WHERE `ckey`='fen_cheng_fee';
UPDATE `tsys_config` SET `remark`='代理人推荐默认分佣比例，注册时使用' WHERE `ckey`='agent_fen_cheng_fee';
