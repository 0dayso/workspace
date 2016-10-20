package org.vetech.core.modules.cache;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisManage<V> {

	/**
	 * 自动注入默认的redis
	 */
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, V> redisTemplate;

	public RedisTemplate<String, V> getRedisTemplate() {
		return redisTemplate;
	}

	/**
	 * 通过bean ref 注入 手动注入 可以指定需要注入的
	 * 
	 * @param redisTemplate
	 */
	public void setRedisTemplate(RedisTemplate<String, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * key value 键值对
	 * 
	 * @param cachename
	 *            缓存的前缀
	 * @param key
	 * @return
	 */
	public V get(String cachename, String key) {
		ValueOperations<String, V> v = redisTemplate.opsForValue();
		return v.get(cachename + key);
	}

	/**
	 * key value 键值对
	 * 
	 * @param cachename
	 *            缓存的前缀
	 * @param key
	 * @return
	 */
	public void remove(String cachename, String key) {
		redisTemplate.delete(cachename + key);
	}

	/**
	 * 模糊查找key
	 * 
	 * @param pattern
	 *            key*等形式
	 * @return
	 */
	public Set<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}

	/**
	 *
	 * @param cachename
	 *            缓存的前缀
	 * @param key
	 * @param o
	 *            缓存的对象
	 * @param second
	 *            秒数
	 */
	public void put(String cachename, String key, V o, long second) {
		ValueOperations<String, V> v = redisTemplate.opsForValue();
		if (second == 0) {
			v.set(cachename + key, o);
		} else {
			v.set(cachename + key, o, second, TimeUnit.SECONDS);
		}
	}

	/**
	 * spop paramK 删除并返回paramK对应set中随机的一个元素,如果set是空或者key不存在返回nil
	 * 
	 * @param paramK
	 * @return
	 */
	public V popSet(String paramK) {
		SetOperations<String, V> set = redisTemplate.opsForSet();
		return set.pop(paramK);
	}

	/**
	 * 将一个或多个member元素加入到集合key当中，已经存在于集合的member元素将被忽略。 假如key不存在，则创建一个只包含member元素作成员的集合。 当key不是集合类型时，返回一个错误。
	 * 
	 * @param paramK
	 * @param o
	 */
	public void putSet(String paramK, V o) {
		SetOperations<String, V> set = redisTemplate.opsForSet();
		set.add(paramK, o);
	}

	/**
	 * 移除集合paramK中的一个或多个member元素，不存在的member元素会被忽略。
	 * 
	 * @param paramK
	 * @param o
	 */
	public void removeSet(String paramK, V o) {
		SetOperations<String, V> set = redisTemplate.opsForSet();
		set.remove(paramK, o);
	}

	/**
	 * 获取ParamK 对应的set集合的元素个数
	 * 
	 * @param paramK
	 * @return
	 */
	public Long sizeSet(String paramK) {
		SetOperations<String, V> set = redisTemplate.opsForSet();
		return set.size(paramK);
	}

	/**
	 * 删除ParamK 对应的set集合的元素
	 * 
	 * @param paramK
	 * @return
	 */
	public void clearSet(String paramK) {
		SetOperations<String, V> set = redisTemplate.opsForSet();
		set.remove(paramK);
	}

	/**
	 * 返回ParamK对应的set集合
	 * 
	 * @param paramK
	 * @return
	 */
	public Set<V> allMembersSet(String paramK) {
		SetOperations<String, V> set = redisTemplate.opsForSet();
		return set.members(paramK);
	}

	/**
	 * list 先进先出队列
	 * 
	 * 尾部添加
	 */
	public void rpush(String paramK, V o) {
		ListOperations<String, V> list = redisTemplate.opsForList();
		list.rightPush(paramK, o);
	}

	/**
	 * list 先进先出队列
	 * 
	 * 头部取出
	 * 
	 * @param cachename
	 * @return
	 */
	public V lpop(String paramK) {
		ListOperations<String, V> list = redisTemplate.opsForList();
		return list.leftPop(paramK);
	}

	/**
	 * list队列长度
	 * 
	 * @param cachename
	 * @return
	 */
	public Long llen(String paramK) {
		ListOperations<String, V> list = redisTemplate.opsForList();
		return list.size(paramK);
	}

}
