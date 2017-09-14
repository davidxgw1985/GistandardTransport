package com.gistandard.transport.system.mongo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @author yujie
 * @ClassName mongoBaseDao
 * @Description
 * @Version 1.0
 * @Date 2016-01-27
 */
@Repository
public abstract class MongoBaseDao<T> {

	@Autowired
	protected MongoTemplate mongoTemplate;

	public void save(T t) {
		this.mongoTemplate.save(t);
	}

	public T queryById(String id) {
		Query query = new Query();
		Criteria criteria = Criteria.where("_id").is(id);
		query.addCriteria(criteria);
		return this.mongoTemplate.findOne(query, this.getEntityClass());
	}

	public List<T> queryList(Query query) {
		return this.mongoTemplate.find(query, this.getEntityClass());
	}

	public T queryOne(Query query) {
		return this.mongoTemplate.findOne(query, this.getEntityClass());
	}

	public List<T> getPage(Query query, int start, int size) {
		query.skip(start);
		query.limit(size);
		List<T> lists = this.mongoTemplate.find(query, this.getEntityClass());
		return lists;
	}

	public Long getPageCount(Query query) {
		return this.mongoTemplate.count(query, this.getEntityClass());
	}

	public void deleteById(String id) {
		Criteria criteria = Criteria.where("_id").in(id);
		if (null != criteria) {
			Query query = new Query(criteria);
			if (null != query && this.queryOne(query) != null) {
				mongoTemplate.findAllAndRemove(query, getEntityClass());
			}
		}
	}

	public void delete(T t) {
		this.mongoTemplate.remove(t);
	}

	/**
	 * 更新满足条件的第一个记录
	 * 
	 * @param query
	 * @param update
	 * @author <a href='mailto:dennisit@163.com'>Cn.苏若年(En.dennisit)</a> Copy
	 *         Right since 2013-10-13 下午03:47:10
	 */
	public void updateFirst(Query query, Update update) {
		this.mongoTemplate.updateFirst(query, update, this.getEntityClass());
	}

	public void updateMulti(Query query, Update update) {
		this.mongoTemplate.updateMulti(query, update, this.getEntityClass());
	}

	public void updateInser(Query query, Update update) {
		this.mongoTemplate.upsert(query, update, this.getEntityClass());
	}

	protected abstract Class<T> getEntityClass();
}
