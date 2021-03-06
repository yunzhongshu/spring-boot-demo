## maven-release-plugin管理版本号

### 配置pom.xml
```xml
<project>
<!-- ... -->

<!-- add scm -->
<scm>
    <developerConnection>scm:git:[.git格式的地址(git协议、http协议都可)]</developerConnection>
</scm>

<!-- ... -->
<!-- other configs -->

<!-- add plugin -->
<build>   
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-release-plugin</artifactId>
            <version>2.5.3</version>
            <configuration>
                <tagNameFormat>v@{project.version}</tagNameFormat>
                <autoVersionSubmodules>true</autoVersionSubmodules>
                <arguments>
                    -Dmaven.javadoc.skip=true   <!-- ignore generate javadoc -->
                </arguments>
            </configuration>
        </plugin>
    </plugins>  
</build> 

</project>
```
### 命令执行
```bash
mvn release:prepare # 准备版本发布
# other
mvn release:rollback # 回退release:prepare所执行的操作, 
# tag需要手动删除
git tag -d vxxxx;  # 删除本地tag
git push origin :vxxxx  # 删除远程tag

mvn release:perform  # 在prepare的基础上执行mvn deploy打包构建至仓库
```

执行mvn release:prepare过程中会询问输入release版本号,tag,及新的开发版本号,一般使用默认的就可以了，开发版本会在release版本号上自动加1，并加上-SNAPSHOT:

```bash
...
[INFO] Checking dependencies and plugins for snapshots ...
What is the release version for "demo"? (com.zmdj:demo) 0.0.1: :  #[不输入按前面默认]
What is SCM release tag or label for "demo"? (com.zmdj:demo) v0.0.1: :  #[不输入按前面默认]
What is the new development version for "demo"? (com.zmdj:demo) 0.0.2-SNAPSHOT: :  #[不输入按前面默认]
```

