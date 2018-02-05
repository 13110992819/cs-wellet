
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`company_code`,`system_code`) VALUES ('SYS_ACOUNT_SC','SYS_USER','平台SC盈亏账户','P','0','SC',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-COIN000017','CD-COIN000017');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`company_code`,`system_code`) VALUES ('SYS_ACOUNT_SC_COLD','SYS_USER_COLD','平台SC冷钱包','P','0','SC',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-COIN000017','CD-COIN000017');

UPDATE `tstd_account` SET `user_id`='SYS_USER' WHERE `account_number`='SYS_ACOUNT_ETH';
UPDATE `tstd_account` SET `user_id`='SYS_USER_COLD' WHERE `account_number`='SYS_ACOUNT_ETH_COLD';

UPDATE `tcoin_eth_address` SET `user_id`='SYS_USER' WHERE `user_id`='SYS_USER_ETH';


