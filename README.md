# wxshop
springboot+jpa微信订餐
1部署过程
1) 项目打包
  	>>mvn clean package -Dmaven.test.skip=true
    打包会生成wxshop.jar文件
2) 项目部署
    >>java -jar -Dserver.port=8085 -Dspring.profiles.active=prod wxshop.jar
