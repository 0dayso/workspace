package org.vetech.core.modules.service;

import java.util.List;
import java.util.Map;

import org.vetech.core.modules.exception.BusinessException;
import org.vetech.core.modules.mybatis.mapper.Mapper;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.mybatis.page.Page;
/**
 * 覆盖父类有异常的方法统一抛BusinessException
 * @author 章磊
 *
 * @param <T>
 * @param <M>
 */
public abstract class BaseService<T extends AbstractPageEntity, M extends Mapper<T>> extends MBaseService<T, M>{



	@Override
	public int insert(T t) throws BusinessException {
		try{
			return super.insert(t);
		}catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public int update(T t) throws BusinessException {
		try{
			return super.update(t);
		}catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public int updateSelective(T t) throws BusinessException {
		try{
			return super.updateSelective(t);
		}catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<T> queryList(T t) throws BusinessException {
		try{
			return super.queryList(t);
		}catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public T getEntityById(T t) {
		return super.getEntityById(t);
	}

	@Override
	public Page queryPage(Map param, int pageNum, int pageSize) {
		return super.queryPage(param, pageNum, pageSize);
	}

	@Override
	public List<?> selectDynamicSQL(Map param) {
		return super.selectDynamicSQL(param);
	}
}
