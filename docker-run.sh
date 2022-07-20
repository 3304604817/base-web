# 刷新环境变量
source /etc/profile
source ~/.bash_profile

dirName='basic'
appName='basic'
port=8086

echo ">>> 拉取新代码并打包"
cd /home/app/$dirName
git pull
mvn clean package -U -DskipTests=true

echo ">>> docker stop"
docker stop ${appName}

echo ">>> docker rm"
docker rm ${appName}

echo ">>> cd target"
cd target

echo ">>> 启动并指定日志最大为10M"
docker run -d -p ${port}:${port} \
 --name ${appName} jdk1.8 java -jar -Xms512m -Xmx1024m /home/app/$dirName/target/app.jar \
 --log-opt max-size=10m --log-opt max-file=1