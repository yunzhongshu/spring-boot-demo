## redis 使用

### 使用 spring-boot-autoconfigure 自动配置redis

Pom.xml 添加:

```xml
        <!-- 自动配置 -->
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
        </dependency>
		<!-- redis -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
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

存在RedisOperations.class(该类在包spring-boot-starter-data-redis中存在), 并且@import了LettuceConnectionConfiguration,JedisConnectionConfiguration类, 通过引入的jar来确定使用哪种redis连接方式，读取redis的连接属性及其他相关属性.

> LettuceConnectionConfiguration使用Netty,线程安全；JedisConnectionConfiguration线程不安全，需要用连接池. 因此建议使用LettuceConnectionConfiguration. spring-boot-starter-data-redis默认引入了lettuce包，因此会默认加载LettuceConnectionConfiguration.class, 因为加载条件为@ConditionalOnClass(RedisClient.class), 而JedisConnectionConfiguration:@ConditionalOnClass({ GenericObjectPool.class, JedisConnection.class, Jedis.class }) 需要引入jedis包

> 注：以上类的初始化需要在application.properties(application.yml)中配置redis相关属性，配置信息见类: RedisProperties.class.  以spring.redis.开头


application.properties范例:
```
#spring.redis.password = xxxx
#spring.redis.sentinel.nodes = xx.xx.xx.xx:7379,xx.xx.xx.xx:7479,xx.xx.xx.xx:7579
#spring.redis.sentinel.master = xxx
#spring.redis.database = 1
spring.redis.host = 127.0.0.1
spring.redis.port = 6379
spring.redis.password = xxxx
spring.redis.database = 0
```



### 代码中使用

```java
// 注入redisTemplate, 
// 使用 StringRedisTemplate， 默认采用的是String的序列化策略;
// 使用 RedisTemplate, 默认采用的是JDK的序列化策略;
@Resource
private StringRedisTemplate redisTemplate;  
```

#### strings 操作

```java

        // set key value
        redisTemplate.opsForValue().set(key, value);

        // set key value px 10000

        redisTemplate.opsForValue().set(key, value, 10000L, TimeUnit.MILLISECONDS);

        // set key value ex 10
        redisTemplate.opsForValue().set(key, value, 10, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(10));

        // set key value nx
        redisTemplate.opsForValue().setIfAbsent(key, value);

        // set key value nx ex 10
        redisTemplate.opsForValue().setIfAbsent(key, value, 10, TimeUnit.SECONDS);
        redisTemplate.opsForValue().setIfAbsent(key, value, Duration.ofSeconds(10));

        // set key value xx
        redisTemplate.opsForValue().setIfPresent(key, value);

        // set key value xx ex 10
        redisTemplate.opsForValue().setIfPresent(key, value, 10, TimeUnit.SECONDS);
        redisTemplate.opsForValue().setIfPresent(key, value, Duration.ofSeconds(10));

        // get key
        redisTemplate.opsForValue().get(key);

        // incr key
        redisTemplate.opsForValue().increment(key);

        // incrby key 2
        redisTemplate.opsForValue().increment(key, 2);

        // decr key
        redisTemplate.opsForValue().decrement(key);

        // decr key 2
        redisTemplate.opsForValue().decrement(key, 2);

        // getset key value; set a key to a newvalue, returning the old value as the result.
        String oldValue = redisTemplate.opsForValue().getAndSet(key, value);

        // mset key1 value1 key2 value2
        String key2 = "key2";
        String value2 = "value2";
        redisTemplate.opsForValue().multiSet(new HashMap<String, String>(){
            {
                put(key, value);
                put(key2, value2);
            }
        });

        // mget key1 key2
        redisTemplate.opsForValue().multiGet(new ArrayList<String>(){
            {
                add(key);
                add(key2);
            }
        });

        // del key
        redisTemplate.delete(key);

        // type key
        redisTemplate.type(key);

        // expire key 10
        redisTemplate.expire(key, 10, TimeUnit.SECONDS);

        // persist key
        redisTemplate.persist(key);

```

#### hashes 操作

```java
        HashOperations hashOperations = redisTemplate.opsForHash();

        String key = "user:1000";

        // hmset user:1000 username antirez birthyear 1977 verified 1
        hashOperations.putAll(key, new HashMap(){
            {
                put("username", "antirez");
                put("birthyear", 1977);
                put("verified", 1);
            }
        });

        // hget user:1000 username
        String userName = hashOperations.get(key, "username").toString();

        // hget user:1000 username birthyear verified
        List<String> valueList = hashOperations.multiGet(key, new ArrayList(){
            {
                add("username");
                add("birthyear");
                add("verified");
            }
        });

        // hgetall user:1000
        valueList = hashOperations.values(key);

        // hincrby user:1000 birthyear 10
        hashOperations.increment(key, "birthyear", 10);
```

