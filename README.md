ean package -Dmaven.test.skip=true
    打包会生成wxshop.jar文件
2) 项目部署
    >>java -jar -Dserver.port=8085 -Dspring.profiles.active=prod wxshop.jar

mvn clean package -Dmaven.test.skip=true

//指定端口启动 
java -jar -Dserver.port=8090 sell.jar

//指定生产环境 

java -jar -Dserver.port=8090 -Dspring.profiles.active=prod sell.jar

//指定baseUrl启动
java -jar -Dserver.port=8090 -Dspring.profiles.active=prod -Dwxshop.baseUrl=http://127.0.0.1:8090 sell.jar

// 后台启动
nohup java -jar -Dserver.port=8090 -Dspring.profiles.active=prod sell.jar > //null 2>&1 &
