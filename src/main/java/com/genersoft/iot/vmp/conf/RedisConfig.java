package com.genersoft.iot.vmp.conf;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.parser.ParserConfig;
import com.genersoft.iot.vmp.utils.redis.FastJsonRedisSerializer;

/**
 * @Description:Redis中间件配置类，使用spring-data-redis集成，自动从application.yml中加载redis配置
 * @author: songww
 * @date: 2019年5月30日 上午10:58:25
 * 
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		StringRedisSerializer keySerializer = new StringRedisSerializer();
		template.setKeySerializer(keySerializer);
		template.setHashKeySerializer(keySerializer);
		GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();
		template.setValueSerializer(valueSerializer);
		template.setHashValueSerializer(valueSerializer);
		return template;
	}

	/**
	 * redis消息监听器容器 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
	 * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
	 * 
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
}
