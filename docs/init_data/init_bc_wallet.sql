
/*
-- Query: SELECT * FROM std_account.tstd_account where user_id = 'CD-COIN000017'
-- Date: 2016-12-29 15:08
*/
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`company_code`,`system_code`) VALUES ('SYS_ACOUNT_ETH','SYS_USER_ETH','平台ETH盈亏账户','P','0','ETH',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`company_code`,`system_code`) VALUES ('SYS_ACOUNT_ETH_COLD','SYS_USER_ETH_COLD','平台ETH冷钱包','P','0','ETH',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`company_code`,`system_code`) VALUES ('SYS_ACOUNT_SC','SYS_USER_SC','平台SC盈亏账户','P','0','SC',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`company_code`,`system_code`) VALUES ('SYS_ACOUNT_SC_COLD','SYS_USER_SC_COLD','平台SC冷钱包','P','0','SC',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-COIN000017','CD-COIN000017');

/*
-- Query: select `type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code` from tsys_config
-- Date: 2017-08-23 10:21
*/
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('collection_rule','collection_limit_eth','1','admin',now(),'当ETH地址达到该数量时进行自动归集','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('collection_rule','collection_limit_sc','10','admin',now(),'当SC钱包达到该数量时进行自动归集','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('withdraw_rule','withdraw_fee_eth','0.01','admin',now(),'ETH取现手续费','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('withdraw_rule','withdraw_fee_sc','1','admin',now(),'SC取现手续费','CD-COIN000017','CD-COIN000017');




INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'currency','货币','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','currency','CNY','人民币','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','currency','USD','美元','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','currency','HKD','港币','admin',now(),'','CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'coin','货币','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','coin','BTC','比特币','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','coin','ETH','以太币','admin',now(),'','CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'account_type','账户类型','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','account_type','C','C端用户','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','account_type','P','平台用户','admin',now(),'','CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'account_status','账户状态','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','account_status','0','正常','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','account_status','1','程序锁定','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','account_status','2','人工锁定','admin',now(),'','CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'jour_status','流水状态','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_status','1','待对账','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_status','3','已对账且账已平','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_status','4','帐不平待调账审批','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_status','5','已对账且账不平','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_status','6','无需对账','admin',now(),'','CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'channel_type','渠道类型','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','channel_type','ETH','以太坊','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','channel_type','0','内部账','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','channel_type','90','人工线下','admin',now(),'','CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'jour_biz_type_user','用户流水业务类型','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_user','charge','充值','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_user','withdraw','取现','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_user','buy','交易买入','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_user','sell','交易卖出','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_user','tradefee','交易广告费','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_user','withdrawfee','取现手续费','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_user','invite','推荐好友分成','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_user','hc','红冲','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_user','lb','蓝补','admin',now(),'','CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'frezon_jour_biz_type_user','用户冻结流水业务类型','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','frezon_jour_biz_type_user','withdrawfrozen','取现冻结','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','frezon_jour_biz_type_user','withdrawunfrozen','取现解冻','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','frezon_jour_biz_type_user','tradefrozen','交易冻结','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','frezon_jour_biz_type_user','tradeunfrozen','交易解冻','admin',now(),'','CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'jour_biz_type_cold','冷钱包流水业务类型','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_cold','income','收入','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_cold','pay','支出','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_cold','hc','红冲','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_cold','lb','蓝补','admin',now(),'','CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'jour_biz_type_plat','盈亏账户流水业务类型','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_plat','tradefee','交易广告费收入','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_plat','withdrawfee','取现手续费收入','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_plat','mfee','取现矿工费','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_plat','wfee','归集矿工费','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_plat','invite','推荐好友分成支出','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_plat','hc','红冲','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type_plat','lb','蓝补','admin',now(),'','CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'jour_biz_type','用户流水业务类型','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type','charge','充值','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type','withdraw','取现','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type','buy','交易买入','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type','sell','交易卖出','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type','tradefee','交易广告费','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type','withdrawfee','取现手续费','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type','invite','推荐好友分成','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type','income','收入','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type','pay','支出','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type','mfee','取现矿工费','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type','wfee','归集矿工费','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type','hc','红冲','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','jour_biz_type','lb','蓝补','admin',now(),'','CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'charge_status','充值状态','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','charge_status','1','待支付','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','charge_status','2','支付失败','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','charge_status','3','支付成功','admin',now(),'','CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'withdraw_status','取现状态','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','withdraw_status','1','待审批','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','withdraw_status','2','审批不通过','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','withdraw_status','3','审批通过待广播','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','withdraw_status','4','广播中','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','withdraw_status','5','广播失败','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','withdraw_status','6','广播成功','admin',now(),'','CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'collection_status','归集状态','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','collection_status','0','广播中','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','collection_status','1','广播成功','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','collection_status','2','广播失败','admin',now(),'','CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'pay_type','支付方式','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','pay_type','0','支付宝','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','pay_type','1','微信','admin',now(),'','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','pay_type','2','银联转账','admin',now(),'','CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'maddress_status','散取地址状态','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','maddress_status','0','可使用','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','maddress_status','1','广播使用中','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','maddress_status','2','已弃用','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');




