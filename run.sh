echo ">>> 刷新环境变量"
source /etc/profile

# 指定后端目录/jar包名为app/端口号
dirName='basic'
port=8086

echo ">>> 定位服务位置"
cd /home/spring-cloud/$dirName

echo ">>> git pull"
git pull

echo ">>> mvn clean package"
mvn clean package -U -DskipTests=true

# 根据端口号查询对应的pid，并删除服务进程
# netstat -nlp 列举所有进程
# netstat -nlp | grep :$port 根据端口号找到进程
# awk '{print $7}' 打印第7列
# awk -F"/" '{ print $1 }' 以 / 作为分割符打印第一列
echo ">>> 终止旧服务"
pid=$(netstat -nlp | grep :$port | awk '{print $7}' | awk -F"/" '{ print $1 }');
echo $pid
if [  -n  "$pid"  ];  then
    kill  -9  $pid;
fi

echo ">>> cd target"
cd target

echo ">>> start"
nohup java -jar -Xms512m -Xmx1024m /home/spring-cloud/$(dirName)/target/basic.jar > basic.log &
