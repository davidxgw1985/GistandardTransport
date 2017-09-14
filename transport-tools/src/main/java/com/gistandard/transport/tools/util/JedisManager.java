package com.gistandard.transport.tools.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.SerializationUtils;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by shenzhijun on 2016/2/3.
 */

public class JedisManager {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据key，查询db索引区的数据
     * @param key 数据key
     * @return 字节数据，需要反序列化后使用
     * @throws Exception
     */
    public byte[] getValueByKey(byte[] key){
        return  (byte[])redisTemplate.opsForValue().get(key);
    }

    public Object getValueByKey(Object key){
        return  redisTemplate.opsForValue().get(key);
    }

    public Object getAndSetByKey(Object key, Object value){
        return  redisTemplate.opsForValue().getAndSet(key, value);
    }

    public boolean hasKey(Object key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 根据key，删除db索引区的数据
     * @param key
     * @throws Exception
     */
    public void deleteByKey(byte[] key) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }

    public void deleteByKey(Object key){
        redisTemplate.opsForValue().getOperations().delete(key);
    }


    /**
     * 根据key，存储db索引区的数据
     * @param key
     * @param value
     * @param expireTime 过期时间
     * @throws Exception
     */
    public void saveValueByKey(byte[] key, byte[] value, int expireTime) {
        if(expireTime>0){
            redisTemplate.opsForValue().set(key,value,expireTime, TimeUnit.SECONDS);
        }else{
            redisTemplate.opsForValue().set(key,value);
        }
    }

    /**
     *永久有效
     * @param key
     * @param value
     * @throws Exception
     */
    public void saveValueByKey(byte[] key, byte[] value) {
        saveValueByKey(key, value, -1);
    }

    /**
     *
     * @param key
     * @param value
     * @param expireTime
     */
    public void saveValueByKey(Object key, Object value, int expireTime)
           {
        if(expireTime>0){
            redisTemplate.opsForValue().set(key,value,expireTime, TimeUnit.SECONDS);
        }else{
            redisTemplate.opsForValue().set(key,value);
        }
    }

    /**
     * 不存在插入 ，存在 返回失败
     *Set {@code value} for {@code key}, only if {@code key} does not exist.
     * @param key
     * @param value
     */
    public Boolean saveValueNX(final Object key, final Object value){
        return  saveValueNX(key,value,300);
    }

    /**
     * 不存在插入 ，存在 返回失败
     *Set {@code value} for {@code key}, only if {@code key} does not exist.
     * @param key
     * @param value
     */
    public Boolean saveValueNX(final Object key, final Object value ,final int seconds){
        if(value == null){
            return null;
        }
        return (Boolean)redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                Boolean result =  connection.setNX(rawKey(key), rawKey(value));
                if(seconds>0){
                    if(result) connection.expire(rawKey(key),seconds);
                }
                return result;
            }
        });
    }

    /**
     *
     * @param key
     * @param value
     */
    public void saveValueByKey(Object key, Object value){
        saveValueByKey(key,value,-1);
    }



    /**
     * 获取数据记录数

     * @return
     */
    public Long dbSize() {
        return redisTemplate.getConnectionFactory().getConnection().dbSize();
    }

    /**
     * 根据【字符串】pattern，获取 db dbIndex 区域的所有key
     * @param pattern 注意此处是正则字符串
     * @return
     */
    public Set<byte[]> keys(String pattern) {
        return redisTemplate.getConnectionFactory().getConnection().keys(pattern.getBytes());
    }


    /**
     * 获取Hash列表
     * @param id
     * @param key
     * @return
     */
    public Object getValueByHash(final Object id, final Object key){

        return  redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {

                return SerializationUtils.deserialize( connection.hGet(rawKey(id),rawKey(key)));
            }
        });
    }

    /**
     *同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * @param id
     * @param key
     * @param value
     */
    public void saveValueByHash(final Object id,final Object key ,final Object value){
        if(value == null){
            return;
        }
          redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.hSet(rawKey(id),rawKey(key),rawKey(value));
            }
        });
    }


    /**
     *Hash表删除
     * @param id
     * @param key
     */
    public Object removeValueByHash(final Object id,final Object key){

        return redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.hDel(rawKey(id),rawKey(key));
            }
        });
    }

    /**
     * 存储在键的散列的所有字段和值
     * @param id
     * @return
     */
    public int getSizeByHash(final Object id){
        return (Integer) redisTemplate.opsForValue().getOperations().execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {

                return connection.hGetAll(rawKey(id)).size();
            }
        });
    }


    /**
     * 存储在键的散列的所有字段和值
     * @param id
     * @return
     */
    public Object getKeysByHash(final Object id){
        return redisTemplate.opsForValue().getOperations().execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {

                return connection.hGetAll(rawKey(id));
            }
        });
    }

    /**
     * 清空db块的数据
     */
    public void flushDB() {
        //redisTemplate.getConnectionFactory().getConnection().flushDb();
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    private byte[] rawKey(Object key) {

        if (key instanceof byte[]) {
            return (byte[]) key;
        }
        return SerializationUtils.serialize(key);
    }

    private   byte[][] rawKeys(final String... strs) {
        byte[][] many = new byte[strs.length][];
        for (int i = 0; i < strs.length; i++) {
            many[i] = rawKey(strs[i]);
        }
        return many;
    }



}
