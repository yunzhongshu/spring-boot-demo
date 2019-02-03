## redis 使用

### 使用 spring-boot-autoconfigure 自动配置redis

Pom.xml 添加:

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
        </dependency>
```

####  如何自动配置

Spring-boot-autoconfigure-xxxx.jar的META-INF中的spring.fatories中添加

```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
...
org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration,\
...
```

以上配置的作用是spring-boot启动的时候(@SpringBootApplication 注解中包含的@EnableAutoConfiguration)会读取spring.factories配置文件中的org.springframework.boot.autoconfigure.EnableAutoConfiguration的值的类文件初始化并注入到spring容器中.

> 自己封装工具包也可以用这种方式来引入，避免直接在启动类中使用 @ComponentScan()来手动加载容器对象。

RedisAutoConfiguration类的内容为:

```java
@Configuration
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
@Import({ LettuceConnectionConfiguration.class, JedisConnectionConfiguration.class })
public class RedisAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(name = "redisTemplate")
	public RedisTemplate<Object, Object> redisTemplate(
			RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

	@Bean
	@ConditionalOnMissingBean
	public StringRedisTemplate stringRedisTemplate(
			RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

}
```

该类在没有其他redisTemplate实例和 stringRedisTemplate的时候会执行, 并且@import了JedisConnectionConfiguration类,该类也注入了RedisConnectionFactory（redis的连接工厂类），读取redis的连接属性及其他相关属性.

> 注：以上类的初始化需要在application.properties(application.yml)中配置redis相关属性，配置信息见类: RedisProperties.class.  以spring.redis.开头







