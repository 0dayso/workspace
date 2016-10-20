package org.vetech.core.modules.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vetech.core.modules.service.ServiceException;

public class BatchMapperUtils<T> {

	private static Logger logger = LoggerFactory.getLogger(BatchMapperUtils.class);
	private SqlSessionTemplate sqlSessionTemplate;
	private Class<T> paramClass;

	public BatchMapperUtils(SqlSessionTemplate sqlSessionTemplate, Class<T> paramClass) {
		this.sqlSessionTemplate = sqlSessionTemplate;
		this.paramClass = paramClass;
	}

	/**
	 * 具体执行的dao，必须覆盖这个方法 如果是update ，批量更新的时候 这里不能有返回值 -2147482646
	 * 
	 * @param dao
	 * @param o
	 */
	public void exe(T dao, Object o) {
	}

	/**
	 * 新获取一个模式为BATCH，自动提交为false的session 如果自动提交设置为true,将无法控制提交的条数，
	 * 
	 * 改为最后统一提交，可能导致内存溢出 通过新的session获取mapper
	 */
	public void batchInsert(List<?> addList, int batchcount) throws ServiceException {
		long t = System.currentTimeMillis();
		SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		// 通过新的session获取mapper
		int size = addList.size();
		T dao = session.getMapper(paramClass);
		int cmt_i = 0;
		int i = 0;
		try {
			for (i = 0; i < size; i++) {
				Object one = addList.get(i);
				exe(dao, one);
				if ((i + 1) % batchcount == 0 || i == size - 1) {
					// 手动每1000个一提交，提交后无法回滚
					session.commit();
					// 清理缓存，防止溢出
					session.clearCache();
					cmt_i = i;
				}
			}
		} catch (Exception e) {
			logger.error("批量操作失败", e);
			// 没有提交的数据可以回滚
			session.rollback();
			for (int c = cmt_i; c < i; c++) {
				Object one = addList.get(c);
				logger.error("批量操作失败" + ReflectionToStringBuilder.toString(one));
			}
			throw new ServiceException(e);
		} finally {
			session.close();
		}
		logger.info("批量操作成功，条数" + size + ",耗时" + (System.currentTimeMillis() - t));
		return;
	}

	public void exe(T dao, Object key, Object value) {
	}

	/**
	 * 新获取一个模式为BATCH，自动提交为false的session 如果自动提交设置为true,将无法控制提交的条数，
	 * 
	 * 改为最后统一提交，可能导致内存溢出 通过新的session获取mapper
	 */
	public void batchMapInsert(Map<Object, Object> addMap, int batchcount) {
		long t = System.currentTimeMillis();
		SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		// 通过新的session获取mapper
		int size = addMap.size();
		T dao = session.getMapper(paramClass);
		try {
			int i = 0;
			Set<Entry<Object, Object>> set = addMap.entrySet();
			Iterator<Entry<Object, Object>> it = set.iterator();
			while (it.hasNext()) {
				Entry<Object, Object> en = it.next();
				exe(dao, en.getKey(), en.getValue());
				if ((i + 1) % batchcount == 0 || i == size - 1) {
					// 手动每1000个一提交，提交后无法回滚
					session.commit();
					// 清理缓存，防止溢出
					session.clearCache();
				}
				i++;
			}
		} catch (Exception e) {
			logger.error("MAP批量操作失败", e);
			// 没有提交的数据可以回滚
			session.rollback();
		} finally {
			session.close();
		}
		logger.info("MAP批量操作成功，条数" + size + ",耗时" + (System.currentTimeMillis() - t));
	}
}
