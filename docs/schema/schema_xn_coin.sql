
--
-- Table structure for table `tcoin_ads`
--

DROP TABLE IF EXISTS `tcoin_ads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tcoin_ads` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `trade_type` varchar(32) NOT NULL COMMENT '广告类型（0购买 1出售）',
  `user_id` varchar(32) NOT NULL COMMENT '发布用户编号',
  `trade_currency` varchar(8) NOT NULL COMMENT '交易币种',
  `trade_coin` varchar(8) NOT NULL COMMENT '数字货币类型',
  `only_trust` varchar(2) NOT NULL COMMENT '是否只有信任的人可以交易',
  `premium_rate` decimal(6,4) NOT NULL COMMENT '溢价率',
  `total_count` decimal(64,0) NOT NULL COMMENT '广告的交易总额',
  `left_count` decimal(64,0) NOT NULL COMMENT '剩余可交易',
  `market_price` decimal(32,3) NOT NULL COMMENT '行情价格',
  `protect_price` decimal(32,3) NOT NULL COMMENT '保护单价',
  `true_price` decimal(32,3) NOT NULL COMMENT '溢价率换算后的真实价格',
  `min_trade` decimal(32,3) DEFAULT NULL COMMENT '单笔最小交易额',
  `max_trade` decimal(32,3) DEFAULT NULL COMMENT '单笔最大交易额',
  `pay_type` varchar(32) NOT NULL COMMENT '付款方式',
  `pay_limit` int(11) DEFAULT NULL COMMENT '付款超时时间（分钟）',
  `status` varchar(4) NOT NULL COMMENT '状态 0=草稿、1=上架中、2=下架',
  `leave_message` text CHARACTER SET utf8mb4 NOT NULL COMMENT '广告留言',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `update_datetime` datetime NOT NULL COMMENT '更新时间',
  `fee_rate` decimal(8,4) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tcoin_ads_display_time`
--

DROP TABLE IF EXISTS `tcoin_ads_display_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tcoin_ads_display_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ads_code` varchar(32) NOT NULL COMMENT '编号',
  `week` char(1) NOT NULL COMMENT '周几',
  `start_time` int(11) NOT NULL COMMENT '开始时间',
  `end_time` int(11) NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ads_week` (`ads_code`,`week`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tcoin_arbitrate`
--

DROP TABLE IF EXISTS `tcoin_arbitrate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tcoin_arbitrate` (
  `code` varchar(32) NOT NULL COMMENT '工单编号',
  `trade_order_code` varchar(32) DEFAULT NULL COMMENT '交易订单编号',
  `yuangao` varchar(32) DEFAULT NULL COMMENT '原告',
  `beigao` varchar(32) DEFAULT NULL COMMENT '被告',
  `reason` text CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '仲裁理由',
  `attach` text CHARACTER SET utf8mb4 COMMENT '附件',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `result` text COMMENT '处理结果',
  `updater` varchar(45) DEFAULT NULL COMMENT '处理人',
  `update_datetime` datetime DEFAULT NULL COMMENT '处理时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tcoin_currency_rate`
--

DROP TABLE IF EXISTS `tcoin_currency_rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tcoin_currency_rate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `currency` varchar(10) NOT NULL COMMENT '币种',
  `refer_currency` varchar(10) NOT NULL COMMENT '参照币种 为CNY',
  `origin` varchar(32) NOT NULL COMMENT '汇率来源',
  `rate` decimal(8,4) NOT NULL COMMENT '汇率',
  `update_datetime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `label` varchar(32) DEFAULT NULL COMMENT '标签',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `initial_balance` decimal(64,0) DEFAULT NULL COMMENT '初始金额',
  `balance` decimal(64,0) DEFAULT NULL COMMENT '余额',
  `available_datetime_start` datetime DEFAULT NULL COMMENT '可用时间起',
  `available_datetime_end` datetime DEFAULT NULL COMMENT '可用时间止',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `abandon_datetime` datetime DEFAULT NULL COMMENT '弃用时间',
  `update_datetime` datetime DEFAULT NULL COMMENT '最后一次更新时间',
  `keystore_name` text DEFAULT NULL COMMENT 'keystore文件名',
  `keystore_content` text DEFAULT NULL COMMENT 'keystore文件内容',
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
-- Table structure for table `tcoin_market`
--

DROP TABLE IF EXISTS `tcoin_market`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tcoin_market` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coin` varchar(10) NOT NULL COMMENT '数字货币',
  `refer_currency` varchar(10) NOT NULL COMMENT '参照法币币种',
  `origin` varchar(32) NOT NULL COMMENT '行情来源',
  `last_price` decimal(15,4) NOT NULL COMMENT '最新成交价格',
  `bid` decimal(15,4) DEFAULT NULL COMMENT '买家期望价格',
  `ask` decimal(15,4) DEFAULT NULL COMMENT '卖家期望价格',
  `mid` decimal(15,4) NOT NULL COMMENT '中间价格',
  `low` decimal(15,4) NOT NULL COMMENT '最低价格',
  `high` decimal(15,4) NOT NULL COMMENT '最高价格',
  `volume` varchar(32) DEFAULT NULL COMMENT '交易量',
  `update_datetime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `origin` (`origin`,`coin`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tcoin_trade_order`
--

DROP TABLE IF EXISTS `tcoin_trade_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tcoin_trade_order` (
  `code` varchar(32) NOT NULL COMMENT '订单编号',
  `type` varchar(32) DEFAULT NULL COMMENT '订单类型',
  `ads_code` varchar(32) DEFAULT NULL COMMENT '广告编号',
  `buy_user` varchar(32) DEFAULT NULL COMMENT '买方',
  `sell_user` varchar(32) DEFAULT NULL COMMENT '卖方',
  `leave_message` text CHARACTER SET utf8mb4 COMMENT '广告留言',
  `trade_currency` varchar(32) DEFAULT NULL COMMENT '交易币种',
  `trade_coin` varchar(32) DEFAULT NULL COMMENT '交易数字货币类型',
  `trade_price` decimal(32,3) DEFAULT NULL COMMENT '交易单价',
  `trade_amount` decimal(32,3) DEFAULT NULL COMMENT '交易总额',
  `fee` decimal(64,0) DEFAULT NULL COMMENT '手续费',
  `count` decimal(64,0) DEFAULT NULL COMMENT '交易数字货币数量',
  `pay_type` varchar(32) DEFAULT NULL COMMENT '付款方式',
  `invalid_datetime` datetime DEFAULT NULL COMMENT '支付超时时间',
  `status` varchar(4) DEFAULT NULL COMMENT '订单状态',
  `sb_comment` varchar(32) DEFAULT NULL COMMENT '卖方对买方的评价',
  `bs_comment` varchar(32) DEFAULT NULL COMMENT '买方对卖方的评价',
  `mark_datetime` datetime DEFAULT NULL COMMENT '标记打款时间',
  `release_datetime` datetime DEFAULT NULL COMMENT '币释放时间',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '最后更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最后更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
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
-- Table structure for table `tstd_cnavigate`
--

DROP TABLE IF EXISTS `tstd_cnavigate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_cnavigate` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `url` varchar(255) DEFAULT NULL COMMENT '访问Url',
  `pic` varchar(255) DEFAULT NULL COMMENT '图片',
  `status` varchar(4) DEFAULT NULL COMMENT '状态(1 显示 0 不显示)',
  `location` varchar(32) DEFAULT NULL COMMENT '位置',
  `order_no` int(11) DEFAULT NULL COMMENT '相对位置编号',
  `belong` varchar(32) DEFAULT NULL COMMENT '属于(1 全局 2 地方默认 3 地方默认编号)',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '父编号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `content_type` varchar(32) DEFAULT NULL COMMENT '内容源类型',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
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
-- Table structure for table `tstd_field_times`
--

DROP TABLE IF EXISTS `tstd_field_times`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_field_times` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `type` varchar(32) DEFAULT NULL COMMENT '类型(1 登录名 2 昵称)',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `times` int(11) DEFAULT NULL COMMENT '修改次数',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
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
-- Table structure for table `tstd_user`
--

DROP TABLE IF EXISTS `tstd_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登陆名',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `login_pwd` varchar(32) DEFAULT NULL COMMENT '登陆密码',
  `login_pwd_strength` char(1) DEFAULT NULL COMMENT '登陆密码强度',
  `kind` varchar(4) DEFAULT NULL COMMENT '用户类型',
  `level` varchar(4) DEFAULT NULL COMMENT '用户等级',
  `user_referee` varchar(32) DEFAULT NULL COMMENT '推荐人',
  `user_referee_level` varchar(4) DEFAULT NULL COMMENT '推荐人等级',
  `id_kind` char(1) DEFAULT NULL COMMENT '证件类型',
  `id_no` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `trade_pwd` varchar(32) DEFAULT NULL COMMENT '安全密码',
  `trade_pwd_strength` char(1) DEFAULT NULL COMMENT '安全密码强度',
  `google_secret` varchar(64) DEFAULT NULL COMMENT '谷歌验证秘钥',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色编号',
  `div_rate1` decimal(18,8) DEFAULT NULL COMMENT '分成比例1',
  `div_rate2` decimal(18,8) DEFAULT NULL COMMENT '分成比例2',
  `trade_rate` decimal(18,8) DEFAULT NULL COMMENT '交易手续费率',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `union_id` varchar(255) DEFAULT NULL COMMENT '联合编号',
  `h5_open_id` varchar(255) DEFAULT NULL COMMENT '公众号开放编号',
  `app_open_id` varchar(255) DEFAULT NULL COMMENT 'app开放编号',
  `gender` char(1) DEFAULT NULL COMMENT '性别(1 男 0 女)',
  `introduce` varchar(255) DEFAULT NULL COMMENT '自我介绍',
  `birthday` varchar(16) DEFAULT NULL COMMENT '生日',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `diploma` varchar(4) DEFAULT NULL COMMENT '学位',
  `occupation` varchar(64) DEFAULT NULL COMMENT '职业',
  `work_time` varchar(4) DEFAULT NULL COMMENT '工作年限',
  `pdf` varchar(255) DEFAULT NULL COMMENT '用户资料',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `longitude` varchar(255) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(255) DEFAULT NULL COMMENT '维度',
  `create_datetime` datetime DEFAULT NULL COMMENT '注册时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `last_login` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_user_relation`
--

DROP TABLE IF EXISTS `tstd_user_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_user_relation` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `to_user` varchar(32) DEFAULT NULL COMMENT '关系人编号',
  `type` varchar(4) DEFAULT NULL COMMENT '关系类型',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `update_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
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
  `cvalue` TEXT DEFAULT NULL COMMENT '值',
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

--
-- Table structure for table `tsys_menu`
--

DROP TABLE IF EXISTS `tsys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsys_menu` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `type` varchar(2) DEFAULT NULL COMMENT '类型',
  `url` varchar(64) DEFAULT NULL COMMENT '请求url',
  `order_no` varchar(8) DEFAULT NULL COMMENT '序号',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '父亲节点',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsys_menu_role`
--

DROP TABLE IF EXISTS `tsys_menu_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsys_menu_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(32) DEFAULT NULL,
  `menu_code` varchar(32) DEFAULT NULL,
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=857 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsys_role`
--

DROP TABLE IF EXISTS `tsys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsys_role` (
  `code` varchar(32) NOT NULL COMMENT '角色编号',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `level` varchar(2) DEFAULT NULL,
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `tstd_blacklist` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `type` varchar(32) DEFAULT NULL COMMENT '拉黑类型',
  `status` varchar(4) DEFAULT NULL COMMENT '状态 0-已删除 1-已生效',
  `create_datetime` datetime DEFAULT NULL COMMENT '拉黑时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` varchar(45) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

