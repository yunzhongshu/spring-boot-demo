## spring boot demo

#### 利用maven-release-plugin进行版本管理
```shell
mvn release:prepare # 准备版本发布

# other
mvn release:rollback # 回退release:prepare所执行的操作

mvn release:perform  # 在prepare的基础上执行mvn deploy打包构建至仓库
```