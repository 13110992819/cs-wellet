
--
-- Table structure for table `tstd_comment`
--

DROP TABLE IF EXISTS `tstd_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_comment` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `score` int(11) DEFAULT NULL COMMENT '评星分数',
  `content` text COMMENT '评论内容',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `commenter` varchar(32) DEFAULT NULL COMMENT '评论人',
  `commenter_name` varchar(32) DEFAULT NULL COMMENT '评论人名称',
  `comment_datetime` datetime DEFAULT NULL COMMENT '评论时间',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `remark` text COMMENT '备注',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '父类编号',
  `order_code` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `entity_code` varchar(32) DEFAULT NULL COMMENT '实体编号',
  `entity_name` varchar(32) DEFAULT NULL COMMENT '实体名称',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_interact`
--

DROP TABLE IF EXISTS `tstd_interact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_interact` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型(1 点赞 2 收藏 3 浏览)',
  `entity_code` varchar(32) DEFAULT NULL COMMENT '实体编号',
  `interacter` varchar(32) DEFAULT NULL COMMENT '交互人',
  `interact_datetime` datetime DEFAULT NULL COMMENT '交互时间',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_keyword`
--

DROP TABLE IF EXISTS `tstd_keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_keyword` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `word` varchar(255) DEFAULT NULL COMMENT '词语',
  `weight` int(11) DEFAULT NULL COMMENT '权重(0-1 0.5以上黑，0.5以下白)',
  `level` varchar(4) DEFAULT NULL COMMENT '作用等级',
  `reaction` varchar(4) DEFAULT NULL COMMENT '反应(1 直接拦截/2 替换**)',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_news`
--

DROP TABLE IF EXISTS `tstd_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_news` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类别',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `keywords` varchar(255) DEFAULT NULL,
  `adv_pic` varchar(255) DEFAULT NULL COMMENT '广告图',
  `pic` varchar(255) DEFAULT NULL COMMENT '图片url',
  `content` text COMMENT '内容',
  `scan_num` int(11) DEFAULT NULL COMMENT '浏览数',
  `comment_num` int(11) DEFAULT NULL COMMENT '评论数',
  `location` varchar(4) DEFAULT NULL COMMENT 'UI位置',
  `order_no` int(11) DEFAULT NULL COMMENT '序号',
  `status` varchar(4) DEFAULT NULL COMMENT '状态(0 未发布 1已发布 2 已下架)',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`) COMMENT '新闻资讯'
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
  `cvalue` varchar(255) DEFAULT NULL COMMENT '值',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsys_dict`
--

DROP TABLE IF EXISTS `tsys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号（自增长）',
  `type` char(1) DEFAULT NULL COMMENT '类型（第一层/第二层）',
  `parent_key` varchar(32) DEFAULT NULL COMMENT '父key',
  `dkey` varchar(32) DEFAULT NULL COMMENT 'key',
  `dvalue` varchar(255) DEFAULT NULL COMMENT '值',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
