package org.vetech.core.modules.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.cache.RedisManage;

/**
 * 分布式锁 使用例子 在springXml中 配置 <bean id="redisManage" class="org.vetech.core.modules.cache.RedisManage"/>
 * 
 * <bean id="redisBillLockHandler" class="org.vetech.core.modules.lock.RedisBillLockHandler"/>
 * 
 * final String key = "需要做并发控制的特征字符串";
 * 
 * IBillIdentify billIdentify = new IBillIdentify() {
 * 
 * public String uniqueIdentify() { return key; }
 * 
 * public int getSingle_expire_time() { return 10; } };
 * 
 * try {
 * 
 * redisBillLockHandler.lock(billIdentify);
 * 
 * //业务代码
 * 
 * }finally { redisBillLockHandler.unLock(billIdentify); }
 * 
 * @author http://blog.csdn.net/java2000_wl
 * @version
 */
@Service
public class RedisBillLockHandler implements IBatchBillLockHandler {
	private static final Logger logger = LoggerFactory.getLogger(RedisBillLockHandler.class);
	@Autowired
	private RedisManage<String> redisManage;

	private String PRE = "LOCK_";

	/**
	 * 获取锁 如果锁可用 立即返回true， 否则立即返回返回false
	 * 
	 * @author
	 * @param billIdentify
	 * @return
	 */
	public boolean tryLock(IBillIdentify billIdentify) {
		return tryLock(billIdentify, 0L, null);
	}

	/**
	 * 锁在给定的等待时间内空闲，则获取锁成功 返回true， 否则返回false
	 * 
	 * @author
	 * @param billIdentify
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public boolean tryLock(IBillIdentify billIdentify, long timeout, TimeUnit unit) {
		String key = getKey(billIdentify);
		try {
			RedisTemplate<String, String> jedis = getResource();
			long nano = System.nanoTime();
			do {
				logger.debug("try lock key: " + key);
				boolean i = jedis.opsForValue().setIfAbsent(key, key);
				if (i) {
					if (billIdentify.getSingle_expire_time() > 0) {
						jedis.expire(key, billIdentify.getSingle_expire_time(), TimeUnit.SECONDS);
					}
					logger.debug("get lock, key: " + key + " , expire in " + billIdentify.getSingle_expire_time() + " seconds.");
					return Boolean.TRUE;
				} else { // 存在锁
					if (logger.isErrorEnabled()) {
						String desc = jedis.opsForValue().get(key);
						logger.error("key: " + key + " locked by another business：" + desc);
					}
				}
				if (timeout == 0) {
					break;
				}
				Thread.sleep(300);
			} while ((System.nanoTime() - nano) < unit.toNanos(timeout));
			return Boolean.FALSE;
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}
		return Boolean.FALSE;
	}

	private String getKey(IBillIdentify billIdentify) {
		String key = PRE + billIdentify.uniqueIdentify();
		return key;
	}

	/**
	 * 如果锁空闲立即返回 获取失败 一直等待
	 * 
	 * @author
	 * @param billIdentify
	 */
	public void lock(IBillIdentify billIdentify) {
		String key = getKey(billIdentify);
		try {
			RedisTemplate<String, String> jedis = getResource();
			do {
				logger.debug("lock key: " + key);
				boolean i = jedis.opsForValue().setIfAbsent(key, key);
				if (i) {
					if (billIdentify.getSingle_expire_time() > 0) {
						jedis.expire(key, billIdentify.getSingle_expire_time(), TimeUnit.SECONDS);
					}
					logger.debug("get lock, key: " + key + " , expire in " + billIdentify.getSingle_expire_time() + " seconds.");
					return;
				} else {
					if (logger.isErrorEnabled()) {
						String desc = jedis.opsForValue().get(key);
						logger.error("key: " + key + " locked by another business：" + desc);
					}
				}
				Thread.sleep(300);
			} while (true);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}
	}

	/**
	 * 释放锁
	 * 
	 * @author
	 * @param billIdentify
	 */
	public void unLock(IBillIdentify billIdentify) {
		List<IBillIdentify> list = new ArrayList<IBillIdentify>();
		list.add(billIdentify);
		unLock(list);
	}

	// /**
	// * 批量获取锁 如果全部获取 立即返回true, 部分获取失败 返回false
	// *
	// * @author
	// * @date 2013-7-22 下午10:27:44
	// * @param billIdentifyList
	// * @return
	// */
	// public boolean tryLock(List<IBillIdentify> billIdentifyList) {
	// return tryLock(billIdentifyList, 0L, null);
	// }
	//
	// /**
	// * 锁在给定的等待时间内空闲，则获取锁成功 返回true， 否则返回false
	// *
	// * @author
	// * @param billIdentifyList
	// * @param timeout
	// * @param unit
	// * @return
	// */
	// public boolean tryLock(List<IBillIdentify> billIdentifyList, long timeout, TimeUnit unit) {
	// try {
	// List<String> needLocking = new CopyOnWriteArrayList<String>();
	// List<String> locked = new CopyOnWriteArrayList<String>();
	// RedisTemplate<String, Object> jedis = getResource();
	// long nano = System.nanoTime();
	// do {
	// // 构建pipeline，批量提交
	// Pipeline pipeline = jedis.pipelined();
	// for (IBillIdentify identify : billIdentifyList) {
	// String key = (String) identify.uniqueIdentify();
	// needLocking.add(key);
	// pipeline.setnx(key, key);
	// }
	// logger.debug("try lock keys: " + needLocking);
	// // 提交redis执行计数
	// List<Object> results = pipeline.syncAndReturnAll();
	// for (int i = 0; i < results.size(); ++i) {
	// Long result = (Long) results.get(i);
	// String key = needLocking.get(i);
	// if (result == 1) { // setnx成功，获得锁
	// jedis.expire(key, DEFAULT_BATCH_EXPIRE_TIME);
	// locked.add(key);
	// }
	// }
	// needLocking.removeAll(locked); // 已锁定资源去除
	//
	// if (CollectionUtils.isEmpty(needLocking)) {
	// return true;
	// } else {
	// // 部分资源未能锁住
	// logger.debug("keys: " + needLocking + " locked by another business：");
	// }
	//
	// if (timeout == 0) {
	// break;
	// }
	// Thread.sleep(500);
	// } while ((System.nanoTime() - nano) < unit.toNanos(timeout));
	//
	// // 得不到锁，释放锁定的部分对象，并返回失败
	// if (!CollectionUtils.isEmpty(locked)) {
	// jedis.del(locked.toArray(new String[0]));
	// }
	// return false;
	// } catch (JedisConnectionException je) {
	// logger.debug(je.getMessage(), je);
	// returnBrokenResource(jedis);
	// } catch (Exception e) {
	// logger.debug(e.getMessage(), e);
	// } finally {
	// returnResource(jedis);
	// }
	// return true;
	// }

	/**
	 * 批量释放锁
	 * 
	 * @author
	 * @param billIdentifyList
	 */
	public void unLock(List<IBillIdentify> billIdentifyList) {
		List<String> keys = new CopyOnWriteArrayList<String>();
		for (IBillIdentify identify : billIdentifyList) {
			String key = getKey(identify);
			keys.add(key);
		}
		try {
			RedisTemplate<String, String> jedis = getResource();
			jedis.delete(keys);
			logger.debug("release lock, keys :" + keys);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}
	}

	/**
	 * @author
	 * @date 2013-7-22 下午9:33:45
	 * @return
	 */
	private RedisTemplate<String, String> getResource() {
		return redisManage.getRedisTemplate();
	}

}