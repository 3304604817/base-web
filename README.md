# SpringBoot单体服务
LayUI+SpringBoot
默认80端口启动
登录密码：admin/Admin@123!

依赖使用须知：
1、添加组件坐标
<repositories>
<repository>
<id>base-repo</id>
<name>私有仓</name>
<url>http://39.101.202.67:18081/repository/maven-public/</url>
</repository>
</repositories>
2、添加依赖
<dependency>
<groupId>com.base</groupId>
<artifactId>base-web</artifactId>
<version>1.0.0-20230612.021929-11</version>
</dependency>
3、在POM中引用当前项目坐标的SpringBoot或Maven项目，需要在的Main添加@EnableBaseWeb

菜单:
    研发工具:
        Json格式化:格式化Json,Base64加密/解密,MD5加密,UUID生成

# 启动项目
nohup java -jar xxx.jar --spring.profiles.active=prod  
--spring.profiles.active=prod  指定读取指定前缀的配置文件

# sharding-jdbc 读写分离
src/main/resources/application.yml

# Jwt认证+拦截
src/main/java/com/base/basic/config/JwtInterceptorConfig.java
src/main/java/com/base/basic/util/jwt

# MinIO

# Swagger3
http://localhost:10010/swagger-ui/index.html#/

# 目录结构
v0：服务架构的基座逻辑代码
vn：业务逻辑代码
