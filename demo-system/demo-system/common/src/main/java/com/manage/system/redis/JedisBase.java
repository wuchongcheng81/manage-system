/**
 * Amendment History
 * <p>
 * Programmer        Time            Amendment summary
 * ---------------------------------------------------
 * Junbo Xie         4/24/2018        Initial version
 */
package com.manage.system.redis;

import com.manage.system.util.ObjectUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Map.Entry;

@Service
public abstract class JedisBase {
    private JedisBaseEnum jedisEnum;
    private Logger logger = LoggerFactory.getLogger(JedisBase.class);

    @Resource
    private JedisPool jedisPool;

    private String keyPrefix;

    public JedisBase() {
    }

    public JedisBaseEnum getJedisEnum() {
        return this.jedisEnum;
    }

    public void setJedisEnum(JedisBaseEnum jedisEnum) {
        this.jedisEnum = jedisEnum;
    }

    public String getKeyPrefix() {
        return this.keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public String get(String key) {
        String value = null;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            if (jedis.exists(this.keyPrefix + key).booleanValue()) {
                value = jedis.get(this.keyPrefix + key);
                value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
            }
        } catch (Exception var8) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return value;
    }

    public Object getObject(String key) {
        Object value = null;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            if (jedis.exists(this.getBytesKey(this.keyPrefix + key)).booleanValue()) {
                value = this.toObject(jedis.get(this.getBytesKey(this.keyPrefix + key)));
            }
        } catch (Exception var8) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return value;
    }

    public String set(String key, String value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            result = jedis.set(this.keyPrefix + key, value);
            if (cacheSeconds != 0) {
                jedis.expire(this.keyPrefix + key, cacheSeconds);
            }
        } catch (Exception var10) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public String set(String key, String value, Date endDate) {
        long cacheSeconds = endDate.getTime() / 1000L - System.currentTimeMillis() / 1000L;
        return cacheSeconds <= 0L ? null : this.set(key, value, (int) cacheSeconds);
    }

    public String setObject(String key, Object value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            result = jedis.set(this.getBytesKey(this.keyPrefix + key), this.toBytes(value));
            if (cacheSeconds != 0) {
                jedis.expire(this.keyPrefix + key, cacheSeconds);
            }
        } catch (Exception var10) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public String setObject(String key, Object value, Date endDate) {
        long cacheSeconds = endDate.getTime() / 1000L - System.currentTimeMillis() / 1000L;
        return cacheSeconds <= 0L ? null : this.setObject(key, value, (int) cacheSeconds);
    }

    public List<String> getList(String key) {
        List<String> value = null;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            if (jedis.exists(this.keyPrefix + key).booleanValue()) {
                value = jedis.lrange(this.keyPrefix + key, 0L, -1L);
            }
        } catch (Exception var8) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return value;
    }

    public List<Object> getObjectList(String key) {
        List<Object> value = null;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            if (jedis.exists(this.getBytesKey(this.keyPrefix + key)).booleanValue()) {
                List<byte[]> list = jedis.lrange(this.getBytesKey(this.keyPrefix + key), 0L, -1L);
                value = Lists.newArrayList();
                Iterator i$ = list.iterator();

                while (i$.hasNext()) {
                    byte[] bs = (byte[]) i$.next();
                    value.add(this.toObject(bs));
                }
            }
        } catch (Exception var10) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return value;
    }

    public long setList(String key, List<String> value, int cacheSeconds) {
        long result = 0L;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            if (jedis.exists(this.keyPrefix + key).booleanValue()) {
                jedis.del(this.keyPrefix + key);
            }

            String[] valueArray = new String[value.size()];

            for (int i = 0; i < value.size(); ++i) {
                valueArray[i] = (String) value.get(i);
            }

            result = jedis.rpush(this.keyPrefix + key, valueArray).longValue();
            if (cacheSeconds != 0) {
                jedis.expire(this.keyPrefix + key, cacheSeconds);
            }
        } catch (Exception var12) {
            this.logger.error(var12.getMessage(), var12);
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public long setObjectList(String key, List<Object> value, int cacheSeconds) {
        long result = 0L;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            if (jedis.exists(this.getBytesKey(this.keyPrefix + key)).booleanValue()) {
                jedis.del(this.keyPrefix + key);
            }

            List<byte[]> list = Lists.newArrayList();
            Iterator i$ = value.iterator();

            while (i$.hasNext()) {
                Object o = i$.next();
                list.add(this.toBytes(o));
            }

            result = jedis.rpush(this.getBytesKey(this.keyPrefix + key), (byte[][]) ((byte[][]) list.toArray())).longValue();
            if (cacheSeconds != 0) {
                jedis.expire(this.keyPrefix + key, cacheSeconds);
            }
        } catch (Exception var13) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public long listAdd(String key, String... value) {
        long result = 0L;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            result = jedis.rpush(this.keyPrefix + key, value).longValue();
        } catch (Exception var10) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public long listObjectAdd(String key, Object... value) {
        long result = 0L;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            List<byte[]> list = Lists.newArrayList();
            Object[] arr$ = value;
            int len$ = value.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                Object o = arr$[i$];
                list.add(this.toBytes(o));
            }

            result = jedis.rpush(this.getBytesKey(this.keyPrefix + key), (byte[][]) ((byte[][]) list.toArray())).longValue();
        } catch (Exception var14) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public Set<String> getSet(String key) {
        Set<String> value = null;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            if (jedis.exists(this.keyPrefix + key).booleanValue()) {
                value = jedis.smembers(this.keyPrefix + key);
            }
        } catch (Exception var8) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return value;
    }

    public Set<Object> getObjectSet(String key) {
        Set<Object> value = null;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            if (jedis.exists(this.getBytesKey(this.keyPrefix + key)).booleanValue()) {
                value = Sets.newHashSet();
                Set<byte[]> set = jedis.smembers(this.getBytesKey(this.keyPrefix + key));
                Iterator i$ = set.iterator();

                while (i$.hasNext()) {
                    byte[] bs = (byte[]) i$.next();
                    value.add(this.toObject(bs));
                }
            }
        } catch (Exception var10) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return value;
    }

    public long setObjectSet(String key, Set<Object> value, int cacheSeconds) {
        long result = 0L;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            if (jedis.exists(this.getBytesKey(this.keyPrefix + key)).booleanValue()) {
                jedis.del(this.keyPrefix + key);
            }

            Set<byte[]> set = Sets.newHashSet();
            Iterator i$ = value.iterator();

            while (i$.hasNext()) {
                Object o = i$.next();
                set.add(this.toBytes(o));
            }

            result = jedis.sadd(this.getBytesKey(this.keyPrefix + key), (byte[][]) ((byte[][]) set.toArray())).longValue();
            if (cacheSeconds != 0) {
                jedis.expire(this.keyPrefix + key, cacheSeconds);
            }
        } catch (Exception var13) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public long setSetAdd(String key, String... value) {
        long result = 0L;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            result = jedis.sadd(this.keyPrefix + key, value).longValue();
        } catch (Exception var10) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public long setSetObjectAdd(String key, Object... value) {
        long result = 0L;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            Set<byte[]> set = Sets.newHashSet();
            Object[] arr$ = value;
            int len$ = value.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                Object o = arr$[i$];
                set.add(this.toBytes(o));
            }

            result = jedis.rpush(this.getBytesKey(this.keyPrefix + key), (byte[][]) ((byte[][]) set.toArray())).longValue();
        } catch (Exception var14) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public Map<String, String> getMap(String key) {
        Map<String, String> value = null;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            if (jedis.exists(this.keyPrefix + key).booleanValue()) {
                value = jedis.hgetAll(this.keyPrefix + key);
            }
        } catch (Exception var8) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return value;
    }

    public Map<String, Object> getObjectMap(String key) {
        Map<String, Object> value = null;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            if (jedis.exists(this.getBytesKey(this.keyPrefix + key)).booleanValue()) {
                value = Maps.newHashMap();
                Map<byte[], byte[]> map = jedis.hgetAll(this.getBytesKey(this.keyPrefix + key));
                Iterator i$ = map.entrySet().iterator();

                while (i$.hasNext()) {
                    Entry<byte[], byte[]> e = (Entry) i$.next();
                    value.put(new String((byte[]) e.getKey(), "UTF-8"), this.toObject((byte[]) e.getValue()));
                }
            }
        } catch (Exception var10) {
            ;
            var10.printStackTrace();
        } finally {
            this.returnResource(jedis);
        }

        return value;
    }

    public String setMap(String key, Map<String, String> value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            if (jedis.exists(this.keyPrefix + key).booleanValue()) {
                jedis.del(this.keyPrefix + key);
            }

            result = jedis.hmset(this.keyPrefix + key, value);
            if (cacheSeconds != 0) {
                jedis.expire(this.keyPrefix + key, cacheSeconds);
            }
        } catch (Exception var10) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public String setObjectMap(String key, Map<String, Object> value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            if (jedis.exists(this.getBytesKey(this.keyPrefix + key)).booleanValue()) {
                jedis.del(this.keyPrefix + key);
            }

            Map<byte[], byte[]> map = Maps.newHashMap();
            Iterator i$ = value.entrySet().iterator();

            while (i$.hasNext()) {
                Entry<String, Object> e = (Entry) i$.next();
                map.put(this.getBytesKey(e.getKey()), this.toBytes(e.getValue()));
            }

            result = jedis.hmset(this.getBytesKey(this.keyPrefix + key), map);
            if (cacheSeconds != 0) {
                jedis.expire(this.keyPrefix + key, cacheSeconds);
            }
        } catch (Exception var12) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public String getByMap(String mapKey, String key) {
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            String var4 = jedis.hget(this.getKeyPrefix() + mapKey, key);
            return var4;
        } catch (Exception var8) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return null;
    }

    public Object getObjectByMap(String mapKey, String key) {
        Jedis jedis = null;

        Object var5;
        try {
            jedis = this.getResource();
            byte[] value = jedis.hget(this.getBytesKey(this.getKeyPrefix() + mapKey), this.getBytesKey(key));
            if (value == null) {
                var5 = null;
                return var5;
            }

            var5 = this.toObject(value);
        } catch (Exception var9) {
            return null;
        } finally {
            this.returnResource(jedis);
        }

        return var5;
    }

    public String mapPut(String key, Map<String, String> value) {
        String result = null;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            result = jedis.hmset(this.keyPrefix + key, value);
        } catch (Exception var9) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public String mapObjectPut(String key, Map<String, Object> value) {
        String result = null;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            Map<byte[], byte[]> map = Maps.newHashMap();
            Iterator i$ = value.entrySet().iterator();

            while (i$.hasNext()) {
                Entry<String, Object> e = (Entry) i$.next();
                map.put(this.getBytesKey(e.getKey()), this.toBytes(e.getValue()));
            }

            result = jedis.hmset(this.getBytesKey(this.keyPrefix + key), map);
        } catch (Exception var11) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public long mapRemove(String key, String mapKey) {
        long result = 0L;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            result = jedis.hdel(this.keyPrefix + key, new String[]{mapKey}).longValue();
        } catch (Exception var10) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public long mapObjectRemove(String key, String mapKey) {
        long result = 0L;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            result = jedis.hdel(this.getBytesKey(this.keyPrefix + key), new byte[][]{this.getBytesKey(mapKey)}).longValue();
        } catch (Exception var10) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public boolean mapExists(String key, String mapKey) {
        boolean result = false;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            result = jedis.hexists(this.keyPrefix + key, mapKey).booleanValue();
        } catch (Exception var9) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public boolean mapObjectExists(String key, String mapKey) {
        boolean result = false;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            result = jedis.hexists(this.getBytesKey(this.keyPrefix + key), this.getBytesKey(mapKey)).booleanValue();
        } catch (Exception var9) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public long setObjectExpire(String key, int cacheSeconds) {
        long result = 0L;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            if (jedis.exists(this.keyPrefix + key).booleanValue()) {
                jedis.expire(this.keyPrefix + key, cacheSeconds);
            }
        } catch (Exception var10) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public long del(String key) {
        long result = 0L;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            if (jedis.exists(this.keyPrefix + key).booleanValue()) {
                result = jedis.del(this.keyPrefix + key).longValue();
            }
        } catch (Exception var9) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public long delObject(String key) {
        long result = 0L;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            if (jedis.exists(this.getBytesKey(this.keyPrefix + key)).booleanValue()) {
                result = jedis.del(this.getBytesKey(this.keyPrefix + key)).longValue();
            }
        } catch (Exception var9) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public boolean exists(String key) {
        boolean result = false;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            result = jedis.exists(this.keyPrefix + key).booleanValue();
        } catch (Exception var8) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public boolean existsObject(String key) {
        boolean result = false;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            result = jedis.exists(this.getBytesKey(this.keyPrefix + key)).booleanValue();
        } catch (Exception var8) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result;
    }

    public Jedis getResource() throws JedisException {
        Jedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            if (this.jedisEnum.equals(JedisBaseEnum.DB0)) {
                jedis.select(0);
            } else if (this.jedisEnum.equals(JedisBaseEnum.DB1)) {
                jedis.select(1);
            } else if (this.jedisEnum.equals(JedisBaseEnum.DB2)) {
                jedis.select(2);
            } else if (this.jedisEnum.equals(JedisBaseEnum.DB3)) {
                jedis.select(3);
            } else if (this.jedisEnum.equals(JedisBaseEnum.DB4)) {
                jedis.select(4);
            } else if (this.jedisEnum.equals(JedisBaseEnum.DB5)) {
                jedis.select(5);
            } else if (this.jedisEnum.equals(JedisBaseEnum.DB6)) {
                jedis.select(6);
            } else if (this.jedisEnum.equals(JedisBaseEnum.DB7)) {
                jedis.select(7);
            } else if (this.jedisEnum.equals(JedisBaseEnum.DB8)) {
                jedis.select(8);
            } else if (this.jedisEnum.equals(JedisBaseEnum.DB9)) {
                jedis.select(9);
            } else if (this.jedisEnum.equals(JedisBaseEnum.DB10)) {
                jedis.select(10);
            } else if (this.jedisEnum.equals(JedisBaseEnum.DB11)) {
                jedis.select(11);
            } else if (this.jedisEnum.equals(JedisBaseEnum.DB12)) {
                jedis.select(12);
            } else if (this.jedisEnum.equals(JedisBaseEnum.DB13)) {
                jedis.select(13);
            } else if (this.jedisEnum.equals(JedisBaseEnum.DB14)) {
                jedis.select(14);
            } else if (this.jedisEnum.equals(JedisBaseEnum.DB15)) {
                jedis.select(15);
            }
            return jedis;
        } catch (JedisException var3) {
            this.returnBrokenResource(jedis);
            throw var3;
        }
    }

    public void returnBrokenResource(Jedis jedis) {
        if (jedis != null) {
            this.jedisPool.returnBrokenResource(jedis);
        }

    }

    public void returnResource(Jedis jedis) {
        if (jedis != null) {
            this.jedisPool.returnResource(jedis);
        }

    }

    public byte[] getBytesKey(Object object) {
        if (object instanceof String) {
            byte[] result = null;

            try {
                result = ((String) object).getBytes("UTF-8");
            } catch (UnsupportedEncodingException var4) {
                ;
            }

            return result;
        } else {
            return ObjectUtil.serialize(object);
        }
    }

    public byte[] toBytes(Object object) {
        return ObjectUtil.serialize(object);
    }

    public Object toObject(byte[] bytes) {
        return ObjectUtil.unserialize(bytes);
    }

    public long lpush(byte[] key, byte[]... value) {
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            long var4 = jedis.lpush(key, value).longValue();
            return var4;
        } catch (Exception var9) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return 0L;
    }

    public long lpush(String key, String... value) {
        Jedis jedis = null;
        try {
            jedis = this.getResource();
            long var4 = jedis.lpush(this.keyPrefix + key, value).longValue();
            return var4;
        } catch (Exception var9) {
        } finally {
            this.returnResource(jedis);
        }
        return 0L;
    }

    public long lpush(String key, Object... value) {
        if (value != null && value.length > 0) {
            byte[][] values = new byte[value.length][];
            for (int i = 0; i < value.length; ++i) {
                values[i] = this.toBytes(value[i]);
            }
            return this.lpush(this.getBytesKey(this.keyPrefix + key), values);
        } else {
            return 0L;
        }
    }

    public long rpush(String key, String... value) {
        Jedis jedis = null;
        try {
            jedis = this.getResource();
            long var4 = jedis.rpush(this.keyPrefix + key, value).longValue();
            return var4;
        } catch (Exception var9) {
        } finally {
            this.returnResource(jedis);
        }
        return 0L;
    }

    public String lpop(String key) {
        Jedis jedis = null;
        String var3;
        try {
            jedis = this.getResource();
            if (!jedis.exists(this.keyPrefix + key).booleanValue()) {
                return null;
            }
            var3 = jedis.lpop(this.keyPrefix + key);
        } catch (Exception var7) {
            return null;
        } finally {
            this.returnResource(jedis);
        }
        return var3;
    }

    public Object lpopObject(String key) {
        return this.toObject(this.lpop(this.getBytesKey(this.keyPrefix + key)));
    }

    public byte[] lpop(byte[] key) {
        Jedis jedis = null;
        byte[] var3;
        try {
            jedis = this.getResource();
            if (!jedis.exists(key).booleanValue()) {
                return null;
            }
            var3 = jedis.rpop(key);
        } catch (Exception var7) {
            return null;
        } finally {
            this.returnResource(jedis);
        }
        return var3;
    }

    public byte[] rpop(byte[] key) {
        Jedis jedis = null;
        byte[] rpop = null;
        try {
            jedis = this.getResource();
            if (jedis.exists(key).booleanValue()) {
                rpop = jedis.rpop(key);
            }
        } catch (Exception var8) {
        } finally {
            this.returnResource(jedis);
        }
        return rpop;
    }

    public String rpop(String key) {
        Jedis jedis = null;
        String rpop = null;
        try {
            jedis = this.getResource();
            if (jedis.exists(this.keyPrefix + key).booleanValue()) {
                rpop = jedis.rpop(this.keyPrefix + key);
            }
        } catch (Exception var8) {
            this.logger.error(var8.getMessage(), var8);
        } finally {
            this.returnResource(jedis);
        }
        return rpop;
    }

    public Object rpopObject(String key) {
        byte[] lpop = this.rpop(this.getBytesKey(this.keyPrefix + key));
        return lpop != null ? this.toObject(lpop) : null;
    }

    public long llen(String key) {
        Long result = 0L;
        Jedis jedis = null;
        try {
            jedis = this.getResource();
            if (jedis.exists(this.keyPrefix + key).booleanValue()) {
                result = jedis.llen(this.keyPrefix + key);
            }
        } catch (Exception var8) {
        } finally {
            this.returnResource(jedis);
        }
        return result.longValue();
    }

    public long incrBy(String key, long integer, Date expireDate) {
        if (expireDate.getTime() <= System.currentTimeMillis()) {
            return 0L;
        } else {
            long cacheSeconds = expireDate.getTime() / 1000L - System.currentTimeMillis() / 1000L;
            return this.incrBy(key, integer, (int) cacheSeconds);
        }
    }

    public long incrBy(String key, long integer) {
        Long result = 0L;
        Jedis jedis = null;
        try {
            jedis = this.getResource();
            result = jedis.incrBy(this.keyPrefix + key, integer);
        } catch (Exception var10) {
        } finally {
            this.returnResource(jedis);
        }
        return result.longValue();
    }

    public long incrBy(String key, long integer, int seconds) {
        Long result = 0L;
        Jedis jedis = null;
        try {
            jedis = this.getResource();
            result = jedis.incrBy(this.keyPrefix + key, integer);
            jedis.expire(this.keyPrefix + key, seconds);
        } catch (Exception var11) {
        } finally {
            this.returnResource(jedis);
        }
        return result.longValue();
    }

    public long decrBy(String key, long integer) {
        Long result = 0L;
        Jedis jedis = null;
        try {
            jedis = this.getResource();
            result = jedis.decrBy(this.keyPrefix + key, integer);
        } catch (Exception var10) {
        } finally {
            this.returnResource(jedis);
        }
        return result.longValue();
    }

    public long decrBy(String key, long integer, int seconds) {
        Long result = 0L;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            result = jedis.decrBy(this.keyPrefix + key, integer);
            jedis.expire(this.keyPrefix + key, seconds);
        } catch (Exception var11) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result.longValue();
    }

    public long expire(String key, int seconds) {
        Long result = 0L;
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            result = jedis.expire(this.keyPrefix + key, seconds);
        } catch (Exception var9) {
            ;
        } finally {
            this.returnResource(jedis);
        }

        return result.longValue();
    }

    public long ttl(String key) {
        Long result = 0L;
        Jedis jedis = null;
        try {
            jedis = this.getResource();
            result = jedis.ttl(this.keyPrefix + key);
        } catch (Exception var8) {
        } finally {
            this.returnResource(jedis);
        }
        return result.longValue();
    }

    public int setnx(String key, String value, int expiry) {
        int result = 0;
        Jedis jedis = null;
        try {
            jedis = this.getResource();
            Long tmpResult = jedis.setnx(this.keyPrefix + key, value);
            if (tmpResult.longValue() > 0L) {
                jedis.expire(this.keyPrefix + key, expiry);
            }
            result = tmpResult.intValue();
        } catch (Exception var10) {
        } finally {
            this.returnResource(jedis);
        }
        return result;
    }
}
