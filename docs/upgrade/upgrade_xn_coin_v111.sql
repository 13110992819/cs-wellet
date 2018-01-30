
/*添加分成比例字段*/
ALTER TABLE `tstd_user` 
CHANGE COLUMN `div_rate` `div_rate1` DECIMAL(18,8) NULL DEFAULT NULL COMMENT '分成比例1' ,
ADD COLUMN `div_rate2` DECIMAL(18,8) NULL COMMENT '分成比例2' AFTER `div_rate1`;
/*默认设置分成比例*/
UPDATE `tstd_user` SET `div_rate1`='0.001', `div_rate2`='0.01' WHERE `kind`='C';

/*合并待交易1和交易中2状态为上架状态1，已下架(3)状态变为2*/
UPDATE `tcoin_ads` SET `status`='1' WHERE `status` in ('2');
UPDATE `tcoin_ads` SET `status`='2' WHERE `status` in ('3');
/*更新广告状态数据字典*/
UPDATE `tsys_dict` SET `dvalue`='已上架' WHERE `parent_key`='ads_status' AND `dkey`='1';
UPDATE `tsys_dict` SET `dvalue`='已下架' WHERE `parent_key`='ads_status' AND `dkey`='2';
DELETE FROM `tsys_dict` WHERE `parent_key`='ads_status' AND `dkey`='3';

/*添加banner位置*/
DELETE FROM `tsys_dict` WHERE `type`='1' AND `parent_key`='banner_location';
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','banner_location','trade','APP交易首页','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','banner_location','market','APP行情首页','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','banner_location','activity','APP邀请好友','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','banner_location','web_banner','WEB首页广告','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','banner_location','web_download','WEB下载二维码','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','banner_location','web_qq','WEBQQ二维码','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','banner_location','web_weibo','WEB微博二维码','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','banner_location','web_wechat','WEB微信二维码','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');

/*添加banner初始化图片*/
INSERT INTO `tstd_cnavigate` (`code`,`name`,`type`,`url`,`pic`,`status`,`location`,`order_no`,`belong`,`parent_code`,`remark`,`content_type`,`company_code`,`system_code`) VALUES ('DH201712201626529747378','web首页banner1','2','','banner_1513758369977.png','1','web_banner',1,'1','0','','1','CD-COIN000017','CD-COIN000017');
INSERT INTO `tstd_cnavigate` (`code`,`name`,`type`,`url`,`pic`,`status`,`location`,`order_no`,`belong`,`parent_code`,`remark`,`content_type`,`company_code`,`system_code`) VALUES ('DH201712201627130809572','web首页banner2','2','','banner2_1513758431226.png','1','web_banner',2,'1','0','','1','CD-COIN000017','CD-COIN000017');
INSERT INTO `tstd_cnavigate` (`code`,`name`,`type`,`url`,`pic`,`status`,`location`,`order_no`,`belong`,`parent_code`,`remark`,`content_type`,`company_code`,`system_code`) VALUES ('DH201712201634317823259','web下载二维码','2','','qrcode_1513758868430.png','1','web_download',1,'1','0','','1','CD-COIN000017','CD-COIN000017');
INSERT INTO `tstd_cnavigate` (`code`,`name`,`type`,`url`,`pic`,`status`,`location`,`order_no`,`belong`,`parent_code`,`remark`,`content_type`,`company_code`,`system_code`) VALUES ('DH201712201635035413821','webqq二维码','2','','qrcode_1513758900219.png','1','web_qq',1,'1','0','','1','CD-COIN000017','CD-COIN000017');
INSERT INTO `tstd_cnavigate` (`code`,`name`,`type`,`url`,`pic`,`status`,`location`,`order_no`,`belong`,`parent_code`,`remark`,`content_type`,`company_code`,`system_code`) VALUES ('DH201712201635218014246','web微博二维码','2','','qrcode_1513758919110.png','1','web_weibo',1,'1','0','','1','CD-COIN000017','CD-COIN000017');
INSERT INTO `tstd_cnavigate` (`code`,`name`,`type`,`url`,`pic`,`status`,`location`,`order_no`,`belong`,`parent_code`,`remark`,`content_type`,`company_code`,`system_code`) VALUES ('DH201712201635404165992','web微信二维码','2','','qrcode_1513758937804.png','1','web_wechat',1,'1','0','','1','CD-COIN000017','CD-COIN000017');

/*添加修改分成比例按钮*/
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201712211119209061370','设置分成比例','2','/setDivRate','7','admin',now(),'','SM201711071721131644746','CD-COIN000017');
/*分配按钮给admin*/
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','SM201712211119209061370','admin',now(),NULL,'CD-COIN000017');

