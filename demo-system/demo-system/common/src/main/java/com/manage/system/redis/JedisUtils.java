/**
 * Amendment History
 * <p>
 * Programmer        Time            Amendment summary
 * ---------------------------------------------------
 * Dason Zhang       2017-08-11      1. Change to singleton pattern; 2. Use StringBuilder instead of contact string within for-loop
 */

package com.manage.system.redis;

import org.springframework.stereotype.Service;

/**
 * Jedis Cache 工具类
 */
@Service
public class JedisUtils extends JedisBase {

    private static volatile JedisUtils instance;

    private JedisUtils() {
        super.setJedisEnum(JedisBaseEnum.DB0);
        super.setKeyPrefix("pinpin:front:");
    }

    /**
     * @return
     */
    public static JedisUtils getInstance() {
        if (instance == null) {
            instance = new JedisUtils();
        }
        return instance;
    }

    public <T> T getObjectByClass(Class<T> resultClass, String... params) {
        StringBuilder key = new StringBuilder();
        key.append(resultClass.getName());
        for (String p : params) {
            key.append("_").append(p);
        }

        return (T) super.getObject(key.toString());
    }

    public void setObjectByClass(Object obj, int cacheSeconds, String... params) {
        StringBuilder key = new StringBuilder();
        key.append(obj.getClass().getName());
        for (String p : params) {
            key.append("_").append(p);
        }
        super.setObject(key.toString(), obj, cacheSeconds);
    }
}
