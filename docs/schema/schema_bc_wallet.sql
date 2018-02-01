
--
-- Table structure for table `tcoin_eth_address`
--

DROP TABLE IF EXISTS `tcoin_eth_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tcoin_eth_address` (
  `code` varchar(32) NOT NULL DEFAULT '' COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '地址类型',
  `address` varchar(255) DEFAULT NULL COMMENT '以太坊地址',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `account_number` varchar(32) DEFAULT NULL,
  `initial_balance` decimal(64,0) DEFAULT NULL COMMENT '初始金额',
  `balance` decimal(64,0) DEFAULT NULL COMMENT '余额',
  `available_datetime_start` datetime DEFAULT NULL COMMENT '可用时间起',
  `available_datetime_end` datetime DEFAULT NULL COMMENT '可用时间止',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `abandon_datetime` datetime DEFAULT NULL COMMENT '弃用时间',
  `update_datetime` datetime DEFAULT NULL COMMENT '最后一次更新时间',
  `keystore_name` text COMMENT 'keystore文件名',
  `keystore_content` text COMMENT 'keystore文件内容',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tcoin_eth_collection`
--

DROP TABLE IF EXISTS `tcoin_eth_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tcoin_eth_collection` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `from_address` varchar(255) DEFAULT NULL COMMENT '被归集地址',
  `to_address` varchar(255) DEFAULT NULL COMMENT '归集地址',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '归集数量',
  `tx_hash` varchar(255) DEFAULT NULL COMMENT '交易hash',
  `tx_fee` decimal(64,0) DEFAULT NULL COMMENT '矿工费',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '发起时间',
  `eth_datetime` datetime DEFAULT NULL COMMENT '网络记账时间',
  `update_datetime` datetime DEFAULT NULL COMMENT '完成时间',
  `ref_no` varchar(32) DEFAULT NULL COMMENT '关联订单号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tcoin_eth_transaction`
--

DROP TABLE IF EXISTS `tcoin_eth_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tcoin_eth_transaction` (
  `hash` varchar(255) NOT NULL COMMENT '交易hash',
  `nonce` text,
  `blockHash` text COMMENT '区块hash',
  `blockNumber` text COMMENT '所属区块号',
  `transactionIndex` text,
  `fromAddress` text COMMENT '出币地址',
  `toAddress` text COMMENT '进币地址',
  `value` text COMMENT '数量',
  `gasPrice` text COMMENT 'gas价格',
  `gas` text COMMENT '最大消耗gas',
  `gasUsed` text COMMENT '时间消耗gas',
  `input` text,
  `creates` text,
  `publicKey` text,
  `raw` text,
  `r` text,
  `s` text,
  `ref_no` varchar(32) DEFAULT NULL COMMENT '关联订单号',
  PRIMARY KEY (`hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tcoin_sc_address`
--

DROP TABLE IF EXISTS `tcoin_sc_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tcoin_sc_address` (
  `code` varchar(32) NOT NULL DEFAULT '' COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '地址类型',
  `address` varchar(255) DEFAULT NULL COMMENT '云储币地址',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '账户编号',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '最后操作人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最后一次更时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tcoin_sc_collection`
--

DROP TABLE IF EXISTS `tcoin_sc_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tcoin_sc_collection` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `from_address` varchar(255) DEFAULT NULL COMMENT '被归集地址',
  `to_address` varchar(255) DEFAULT NULL COMMENT '归集地址',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '归集数量',
  `tx_hash` varchar(255) DEFAULT NULL COMMENT '交易Id',
  `tx_fee` decimal(64,0) DEFAULT NULL COMMENT '矿工费',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '发起时间',
  `sc_datetime` datetime DEFAULT NULL COMMENT '网络记账时间',
  `update_datetime` datetime DEFAULT NULL COMMENT '完成时间',
  `ref_no` varchar(32) DEFAULT NULL COMMENT '关联订单号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tcoin_sc_transaction`
--

DROP TABLE IF EXISTS `tcoin_sc_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tcoin_sc_transaction` (
  `transactionid` varchar(255) NOT NULL COMMENT '交易哈希',
  `confirmationheight` bigint(32) NOT NULL COMMENT '确认区块高度',
  `confirmationtimestamp` bigint(32) NOT NULL COMMENT '确认时间',
  `from` varchar(255) NOT NULL COMMENT '转出地址',
  `to` varchar(255) NOT NULL COMMENT '转入地址',
  `value` varchar(30) NOT NULL COMMENT '交易数量',
  `minerfee` varchar(30) NOT NULL COMMENT '矿工费',
  `ref_no` varchar(32) DEFAULT NULL COMMENT '关联订单号',
  PRIMARY KEY (`transactionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_account`
--

DROP TABLE IF EXISTS `tstd_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_account` (
  `account_number` varchar(32) NOT NULL DEFAULT '' COMMENT '账号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `real_name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '真实姓名',
  `type` varchar(4) DEFAULT NULL COMMENT '类别（B端账号，C端账号，平台账号）',
  `status` varchar(2) DEFAULT NULL COMMENT '状态（正常/程序冻结/人工冻结）',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '余额',
  `frozen_amount` decimal(64,0) DEFAULT NULL COMMENT '冻结金额',
  `md5` varchar(32) DEFAULT NULL COMMENT 'MD5',
  `add_amount` decimal(64,0) DEFAULT '0' COMMENT '累计增加金额',
  `in_amount` decimal(64,0) DEFAULT '0' COMMENT '入金',
  `out_amount` decimal(64,0) DEFAULT '0' COMMENT '出金',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `last_order` varchar(32) DEFAULT NULL COMMENT '最近一次变动对应的流水编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_charge`
--

DROP TABLE IF EXISTS `tstd_charge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_charge` (
  `code` varchar(32) NOT NULL COMMENT '针对编号',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `ref_no` varchar(255) DEFAULT NULL COMMENT '流水分组组号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '针对账号',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '充值金额',
  `account_name` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '针对户名',
  `type` varchar(4) DEFAULT NULL COMMENT '账户类型',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `biz_type` varchar(32) DEFAULT NULL,
  `biz_note` varchar(255) DEFAULT NULL,
  `pay_card_info` varchar(255) DEFAULT NULL COMMENT '支付渠道账号信息',
  `pay_card_no` varchar(255) DEFAULT NULL COMMENT '支付渠道账号',
  `status` varchar(4) NOT NULL COMMENT '状态',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `pay_user` varchar(32) DEFAULT NULL COMMENT '支付回录人',
  `pay_note` varchar(255) DEFAULT NULL COMMENT '支付渠道说明',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`) COMMENT '充值订单'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_exchange_currency`
--

DROP TABLE IF EXISTS `tstd_exchange_currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_exchange_currency` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `to_user_id` varchar(32) DEFAULT NULL COMMENT '去方用户',
  `to_amount` decimal(64,0) DEFAULT NULL COMMENT '去方金额',
  `to_currency` varchar(32) DEFAULT NULL COMMENT '去方币种',
  `from_user_id` varchar(32) DEFAULT NULL COMMENT '来方用户',
  `from_amount` decimal(64,0) DEFAULT NULL COMMENT '来方金额',
  `from_currency` varchar(32) DEFAULT NULL COMMENT '来方币种',
  `create_datetime` datetime DEFAULT NULL COMMENT '产生时间',
  `status` varchar(4) NOT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `pay_type` varchar(4) DEFAULT NULL COMMENT '支付方式',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付编号',
  `pay_amount` bigint(20) DEFAULT NULL COMMENT '支付人民币',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`) COMMENT '币种兑换'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_hlorder`
--

DROP TABLE IF EXISTS `tstd_hlorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_hlorder` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `account_number` varchar(32) NOT NULL COMMENT '账号',
  `account_name` varchar(32) DEFAULT NULL COMMENT '针对户名',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `jour_code` varchar(32) DEFAULT NULL COMMENT '流水号',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `direction` char(1) NOT NULL COMMENT '方向：1=蓝补；0=红冲',
  `amount` bigint(20) NOT NULL COMMENT '金额（精确到厘）',
  `status` varchar(4) NOT NULL COMMENT '状态',
  `apply_user` varchar(32) NOT NULL COMMENT '申请人',
  `apply_note` varchar(255) NOT NULL COMMENT '申请说明',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `approve_user` varchar(32) DEFAULT NULL COMMENT '审批人（li为程序）',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审批说明',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审批时间',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_jour`
--

DROP TABLE IF EXISTS `tstd_jour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_jour` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `kind` varchar(32) DEFAULT NULL COMMENT '流水类型（余额流水、冻结流水）',
  `pay_group` varchar(255) DEFAULT NULL COMMENT '订单分组组号',
  `ref_no` varchar(255) DEFAULT NULL COMMENT '参考订单号',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道类型',
  `channel_order` varchar(255) DEFAULT NULL COMMENT '支付渠道单号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '账号',
  `trans_amount` decimal(64,0) DEFAULT NULL COMMENT '变动金额',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `real_name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '真实姓名',
  `type` varchar(4) DEFAULT NULL COMMENT '账户类型',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `biz_type` varchar(32) DEFAULT NULL COMMENT '业务类型',
  `biz_note` varchar(255) DEFAULT NULL COMMENT '业务类型',
  `pre_amount` decimal(64,0) DEFAULT NULL COMMENT '变动前金额',
  `post_amount` decimal(64,0) DEFAULT NULL COMMENT '变动后金额',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `work_date` varchar(8) DEFAULT NULL COMMENT '拟对账时间',
  `check_user` varchar(32) DEFAULT NULL COMMENT '对账人',
  `check_note` varchar(255) DEFAULT NULL COMMENT '对账说明',
  `check_datetime` datetime DEFAULT NULL COMMENT '对账时间',
  `adjust_user` varchar(32) DEFAULT NULL COMMENT '调账人',
  `adjust_note` varchar(255) DEFAULT NULL COMMENT '调账说明',
  `adjust_datetime` datetime DEFAULT NULL COMMENT '调账时间',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_jour_history`
--

DROP TABLE IF EXISTS `tstd_jour_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_jour_history` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '订单分组组号',
  `ref_no` varchar(32) DEFAULT NULL COMMENT '参考订单号',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道类型',
  `channel_order` varchar(32) DEFAULT NULL COMMENT '支付渠道单号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '账号',
  `trans_amount` bigint(32) DEFAULT NULL COMMENT '变动金额',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `real_name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '真实姓名',
  `type` varchar(4) DEFAULT NULL COMMENT '账户类型',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `biz_type` varchar(32) DEFAULT NULL COMMENT '业务类型',
  `biz_note` varchar(255) DEFAULT NULL COMMENT '业务类型',
  `pre_amount` bigint(32) DEFAULT NULL COMMENT '变动前金额',
  `post_amount` bigint(32) DEFAULT NULL COMMENT '变动后金额',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `work_date` varchar(8) DEFAULT NULL COMMENT '拟对账时间',
  `check_user` varchar(32) DEFAULT NULL COMMENT '对账人',
  `check_note` varchar(255) DEFAULT NULL COMMENT '对账说明',
  `check_datetime` datetime DEFAULT NULL COMMENT '对账时间',
  `adjust_user` varchar(32) DEFAULT NULL COMMENT '调账人',
  `adjust_note` varchar(255) DEFAULT NULL COMMENT '调账说明',
  `adjust_datetime` datetime DEFAULT NULL COMMENT '调账时间',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_withdraw`
--

DROP TABLE IF EXISTS `tstd_withdraw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_withdraw` (
  `code` varchar(32) NOT NULL COMMENT '针对编号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '针对账号',
  `account_name` varchar(32) DEFAULT NULL COMMENT '针对户名',
  `type` varchar(4) DEFAULT NULL COMMENT '类别（B端账号，C端账号，平台账号）',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '取现金额',
  `fee` decimal(64,0) DEFAULT NULL COMMENT '手续费',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `channel_bank` varchar(32) DEFAULT NULL COMMENT '渠道银行代号',
  `channel_order` varchar(255) DEFAULT NULL COMMENT '支付渠道编号',
  `pay_card_info` varchar(255) DEFAULT NULL COMMENT '支付渠道账号信息',
  `pay_card_no` varchar(255) DEFAULT NULL COMMENT '支付渠道账号',
  `status` varchar(4) NOT NULL COMMENT '状态',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_note` varchar(255) DEFAULT NULL COMMENT '申请说明',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `approve_user` varchar(32) DEFAULT NULL COMMENT '审批人',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审批说明',
  `approve_datetime` varchar(32) DEFAULT NULL COMMENT '审批时间',
  `pay_user` varchar(255) DEFAULT NULL COMMENT '支付回录人',
  `pay_note` varchar(255) DEFAULT NULL COMMENT '支付回录说明',
  `pay_group` varchar(255) DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(255) DEFAULT NULL COMMENT '支付渠道订单编号',
  `pay_fee` decimal(64,0) DEFAULT NULL COMMENT '支付渠道手续费（矿工费）',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付回录时间',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`) COMMENT '取现订单'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsys_config`
--

DROP TABLE IF EXISTS `tsys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `ckey` varchar(32) DEFAULT NULL COMMENT 'key值',
  `cvalue` text COMMENT '值',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsys_dict`
--

DROP TABLE IF EXISTS `tsys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号（自增长）',
  `type` char(1) NOT NULL COMMENT '类型（0=下拉框意义 1=下拉框选项）',
  `parent_key` varchar(32) DEFAULT NULL COMMENT '父key',
  `dkey` varchar(32) NOT NULL COMMENT 'key',
  `dvalue` varchar(64) NOT NULL COMMENT '值',
  `updater` varchar(32) NOT NULL COMMENT '更新人',
  `update_datetime` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`) COMMENT '数据字典'
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
