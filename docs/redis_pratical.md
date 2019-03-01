## redis安装部署
### docker 安装redis
```shell
# 查Docker Hub上的redis镜像
$ docker search redis
# 拉取最新版本的redis镜像到本地
$ docker pull redis:latest
# 查看本地redis镜像
$ docker images redis

# 创建redis配置文件
$ cat > redis_6379.conf
bind 127.0.0.1
port 6379
logfile "6379.log"
dbfilename "dump-6379.rdb"

# 运行容器
$ docker run \ 
-p 6379:6379 \ # 端口映射 宿主机:容器
-v $PWD/data_6379:/data:rw \ # 映射数据目录 rw 可读写
-v $PWD/conf/redis.conf:/etc/redis/redis_6379.conf:ro \ #挂载配置文件 ro 为readonly
--privileged=true \ # 给与一些权限
--name redis-server-6379 \ # 给容器起个名字
-d redis:latest redis-server /etc/redis/redis_6379.conf # deamon 运行容器 并用配置文件启动容器内的 redis-server

# 连接、查看容器
$ docker exec -it 43f7a65ec7f8 redis-cli


```