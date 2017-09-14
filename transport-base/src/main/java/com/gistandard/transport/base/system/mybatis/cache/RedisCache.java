package com.gistandard.transport.base.system.mybatis.cache;

import com.gistandard.transport.tools.util.JedisManager;
import com.gistandard.transport.tools.util.SpringContextUtil;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by shenzhijun on 2016/2/17.
 */

public class RedisCache implements Cache {
    private final ReadWriteLock readWriteLock = new DummyReadWriteLock();

    private String id;

    @Autowired
    private JedisManager jedisManager;

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        if (value == null) return;
        initJedisManager();
        jedisManager.saveValueByHash(getId(), key, value);
    }

    @Override
    public Object getObject(Object key) {
        initJedisManager();
        return jedisManager.getValueByHash(getId(), key);
    }

    public int getIdSize() {
        initJedisManager();
        return jedisManager.getSizeByHash(getId());
    }

    @Override
    public Object removeObject(Object key) {
        initJedisManager();
        return jedisManager.removeValueByHash(getId(), key);
    }

    @Override
    public void clear() {
        initJedisManager();
        jedisManager.deleteByKey(getId());
    }

    @Override
    public int getSize() {
        initJedisManager();
        return jedisManager.getSizeByHash(getId());
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    @Override
    public String toString() {
        return "Redis {" + id + "}";
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RedisCache) {
            RedisCache redisCache = (RedisCache) obj;
            return this.getId().equals(redisCache.getId());
        }
        return super.equals(obj);
    }

    public void initJedisManager() {
        if (jedisManager == null) {
            synchronized (this) {
                jedisManager = (JedisManager) SpringContextUtil.getBean("jedisManager");
            }
        }
    }

    public void initJedisTemplate() {
        if (redisTemplate == null) {
            synchronized (this) {
                redisTemplate = (RedisTemplate) SpringContextUtil.getBean("redisTemplate");
            }
        }
    }

    public RedisTemplate getRedisTemplate() {
        if (redisTemplate == null) {
            initJedisTemplate();
        }
        return redisTemplate;
    }
}
