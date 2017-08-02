package net.simpotech.simpo.common;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * BaseDao
 * 
 * @author chenkun
 * @version 1.0
 * @since 2017年8月2日
 */
public class BaseDao extends SqlSessionDaoSupport {

	@Resource
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	/**
	 * 集合查询
	 * 
	 * @param sqlId
	 * @param o
	 * @return List
	 */
	protected <E> List<E> selectList(String sqlId, Object o) {
		if (o != null) {
			return getSqlSession().selectList(sqlId, o);
		} else {
			return getSqlSession().selectList(sqlId);
		}
	}

	/**
	 * 集合查询
	 * 
	 * @param sqlId
	 * @return List
	 */
	protected <E> List<E> selectList(String sqlId) {
		return getSqlSession().selectList(sqlId);
	}

	/**
	 * 单结果查询
	 * 
	 * @param sqlId
	 * @return T
	 */
	protected <T> T selectOne(String sqlId) {
		return getSqlSession().selectOne(sqlId);
	}

	/**
	 * 单结果查询
	 * 
	 * @param sqlId
	 * @param object
	 * @return T
	 */
	protected <T> T selectOne(String sqlId, Object object) {
		return getSqlSession().selectOne(sqlId, object);
	}

	/**
	 * 更新
	 * 
	 * @param sqlId
	 * @param object
	 * @return int
	 */
	protected int update(String sqlId, Object object) {
		return getSqlSession().update(sqlId, object);
	}

	/**
	 * 更新
	 * 
	 * @param sqlId
	 * @param object
	 * @return int
	 */
	protected int update(String sqlId) {
		return getSqlSession().update(sqlId);
	}

	/**
	 * 插入
	 * @param sqlId
	 * @param obj
	 * @return int
	 */
	protected int insert(String sqlId, Object obj) {
		return getSqlSession().insert(sqlId, obj);
	}

	/**
	 * 插入
	 * @param sqlId
	 * @return int
	 */
	protected int insert(String sqlId) {
		return getSqlSession().insert(sqlId);
	}

	/**
	 * 删除
	 * @param sqlId
	 * @return int
	 */
	protected int delete(String sqlId) {
		return getSqlSession().delete(sqlId);
	}

	/**
	 * 删除 
	 * @param sqlId
	 * @param param
	 * @return int
	 */
	protected int delete(String sqlId, Object param) {
		return getSqlSession().delete(sqlId, param);
	}

}
