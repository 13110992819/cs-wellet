部署步骤：
1，eclipse导出war包

2. 本地war包上传至服务器
   scp xn-ylq.war root@121.43.101.148:/home
   cdkj123456Q

3. 备份原先配置(如果第一次部署，跳过此步骤)
  ssh root@121.43.101.148
  cdkj123456Q

  cd /home/wwwroot/ylq/tomcat_ylq_biz/webapps
  cp ./xn-ylq/WEB-INF/classes/application.properties .
  cp ./xn-ylq/WEB-INF/classes/config.properties .
  rm -rf xn-ylq*
  mv /home/xn-ylq.war .
   
4. 已备份配置文件放回原处,重启tomcat
  mv -f application.properties ./xn-ylq/WEB-INF/classes/
  mv -f config.properties ./xn-ylq/WEB-INF/classes/
  
  ../bin/shutdown.sh
  ../bin/startup.sh
  
6. 验证程序
http://121.43.101.148:3702/xn-ylq/api

