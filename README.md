# SpringBoot单体服务
LayUI+SpringBoot
默认80端口启动
登录密码：admin/Admin@123!

菜单:
    研发工具:
        Json格式化:格式化Json,Base64加密/解密,MD5加密,UUID生成

# 启动项目
java -jar xxx.jar --spring.profiles.active=prod  
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
