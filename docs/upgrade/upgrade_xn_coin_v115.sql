
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'maddress_status','散取地址状态','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','maddress_status','0','可使用','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','maddress_status','1','广播使用中','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','maddress_status','2','已弃用','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'zc_sms_notice','仲裁通知手机号','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','zc_sms_notice','18117221191','18117221191','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'qx_sms_notice','取现通知手机号','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','qx_sms_notice','18117221191','18117221191','admin',now(),NULL,'CD-COIN000017','CD-COIN000017');

/*
-- Query: SELECT code,name,type,url,order_no,'admin' updater, now() as update_datetime,remark,parent_code,system_code FROM tsys_menu where update_datetime >= '2018-01-18 00:00:00' and update_datetime <= '2018-01-18 23:59:59'
-- Date: 2018-01-19 09:51
*/
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201801181433365804067','手动归集','2','/shoudongGuiji','3','admin',now(),'','SM201711081409307984995','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201801181529251425569','仲裁通知人','1','/biz/zcNotify.htm','2','admin',now(),'','SM201711181351035579061','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201801181732180944889','新增','2','/add','1','admin',now(),'','SM201801181529251425569','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201801181732407468832','删除','2','/delete','2','admin',now(),'','SM201801181529251425569','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201801181844518257508','提币通知人','1','/finance/TBNotify.htm','4','admin',now(),'','SM201711081416278853402','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201801181845154993080','新增','2','/add','1','admin',now(),'','SM201801181844518257508','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201801181845371542537','删除','2','/delete','2','admin',now(),'','SM201801181844518257508','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201801182150217479792','弃用','2','/dele','2','admin',now(),'','SM201711081421358326980','CD-COIN000017');

/*
-- Query: SELECT `role_code`,`menu_code`,`updater`,now() as `update_datetime`,`remark`,`system_code` FROM tsys_menu_role where system_code = 'CD-COIN000017' and menu_code in (


SELECT code FROM tsys_menu where update_datetime >= '2018-01-18 00:00:00' and update_datetime <= '2018-01-18 23:59:59')
-- Date: 2018-01-19 09:54
*/
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','SM201801181433365804067','admin','2018-01-19 02:11:40',NULL,'CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','SM201801182150217479792','admin','2018-01-19 02:11:40',NULL,'CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','SM201801181844518257508','admin','2018-01-19 02:11:40',NULL,'CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','SM201801181845154993080','admin','2018-01-19 02:11:40',NULL,'CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','SM201801181845371542537','admin','2018-01-19 02:11:40',NULL,'CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','SM201801181529251425569','admin','2018-01-19 02:11:40',NULL,'CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','SM201801181732180944889','admin','2018-01-19 02:11:40',NULL,'CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','SM201801181732407468832','admin','2018-01-19 02:11:40',NULL,'CD-COIN000017');
