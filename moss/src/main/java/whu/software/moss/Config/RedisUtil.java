package whu.software.moss.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;
import whu.software.moss.Service.AccountService;

import java.util.List;
import java.util.Set;


@Component
public class RedisUtil {
    private static final Logger LOG = LoggerFactory.getLogger(AccountService.class);
    @Autowired
    private JedisPool jedisPool;

    public static void returnResource(JedisPool jedisPool, Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
    public String type(String key) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = jedisPool.getResource();
            res = jedis.type(key);
        } catch (Exception e) {

            LOG.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return res;
    }
    public Set<String> keys(String pattern) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = jedisPool.getResource();
            res = jedis.keys(pattern);
        } catch (Exception e) {

            LOG.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return res;
    }
    /**
     * <p>
     * 以秒为单位，返回给定 key 的剩余生存时间
     * </p>
     *
     * @param key
     * @return 当 key 不存在时，返回 -2 。当 key 存在但没有设置剩余生存时间时，返回 -1 。否则，以秒为单位，返回 key
     *         的剩余生存时间。 发生异常 返回 0
     */
    public Long ttl(String key,int indexdb) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            return jedis.ttl(key);
        } catch (Exception e) {

            LOG.error(e.getMessage());
            return 0L;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }
    /**
     * <p>
     * 通过key获取储存在redis中的value
     * </p>
     * <p>
     * 并释放连接
     * </p>
     *
     * @param key
     * @param indexdb 选择redis库 0-15
     * @return 成功返回value 失败返回null
     */
    public String get(String key,int indexdb) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            value = jedis.get(key);
            LOG.info(value);
        } catch (Exception e) {

            LOG.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return value;
    }
    /**
     * <p>
     * 向redis存入key和value,并释放连接资源
     * </p>
     * <p>
     * 如果key已经存在 则覆盖
     * </p>
     *
     * @param key
     * @param value
     * @param indexdb 选择redis库 0-15
     * @return 成功 返回OK 失败返回 0
     */
    public String set(String key, String value,int indexdb) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            return jedis.set(key, value);
        } catch (Exception e) {

            LOG.error(e.getMessage());
            return "0";
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * <p>
     * 通过key返回list的长度
     * </p>
     *
     * @param key
     * @return
     */
    public Long llen(String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = jedisPool.getResource();
            res = jedis.llen(key);
        } catch (Exception e) {

            LOG.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return res;
    }




    /**
     * <p>
     * 返回给定排序后的结果
     * </p>
     *
     * @param key
     * @param sortingParameters
     * @return 返回列表形式的排序结果
     */
    public List<String> sort(String key, SortingParams sortingParameters) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sort(key, sortingParameters);
        } catch (Exception e) {

            LOG.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return null;
    }

    /**
     * <p>
     * 通过key获取list指定下标位置的value
     * </p>
     * <p>
     * 如果start 为 0 end 为 -1 则返回全部的list中的value
     * </p>
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lrange(String key, long start, long end, int indexdb) {
        Jedis jedis = null;
        List<String> res = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            res = jedis.lrange(key, start, end);
        } catch (Exception e) {

            LOG.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return res;
    }

    /**
     * <p>
     * 将列表 key 下标为 index 的元素的值设置为 value
     * </p>
     *
     * @param key
     * @param index
     * @param value
     * @return 操作成功返回 ok ，否则返回错误信息
     */
    public String lset(String key, long index, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lset(key, index, value);
        } catch (Exception e) {

            LOG.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return null;
    }
    /**
     * <p>
     * 通过key从对应的list中删除指定的count个 和 value相同的元素
     * </p>
     *
     * @param key
     * @param count
     *            当count为0时删除全部
     * @param value
     * @return 返回被删除的个数
     */
    public Long lrem(String key, long count, String value) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = jedisPool.getResource();
            res = jedis.lrem(key, count, value);
        } catch (Exception e) {

            LOG.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return res;
    }
    /**
     * <p>
     * 通过key从list的头部删除一个value,并返回该value
     * </p>
     *
     * @param key
     * @return
     */
    synchronized public String lpop(String key) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = jedisPool.getResource();
            res = jedis.lpop(key);
        } catch (Exception e) {

            LOG.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return res;
    }




    /**
     * <p>
     * 通过key向list头部添加字符串
     * </p>
     *
     * @param key
     * @param strs
     *            可以使一个string 也可以使string数组
     * @return 返回list的value个数
     */
    public Long lpush(int indexdb, String key, String... strs) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            res = jedis.lpush(key, strs);
        } catch (Exception e) {

            LOG.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return res;
    }
    /**
     * <p>
     * 通过key从list尾部删除一个value,并返回该元素
     * </p>
     *
     * @param key
     * @return
     */
    synchronized public String rpop(String key, int indexdb) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            res = jedis.rpop(key);
        } catch (Exception e) {

            LOG.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return res;
    }


    /**
     * <p>
     * 通过key从一个list的尾部删除一个value并添加到另一个list的头部,并返回该value
     * </p>
     * <p>
     * 如果第一个list为空或者不存在则返回null
     * </p>
     *
     * @param srckey
     * @param dstkey
     * @return
     */
    public String rpoplpush(String srckey, String dstkey, int indexdb) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            res = jedis.rpoplpush(srckey, dstkey);
        } catch (Exception e) {

            LOG.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return res;
    }
    /**
     * <p>
     * 通过key获取list中指定下标位置的value
     * </p>
     *
     * @param key
     * @param index
     * @return 如果没有返回null
     */
    public String lindex(String key, long index) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = jedisPool.getResource();
            res = jedis.lindex(key, index);
        } catch (Exception e) {

            LOG.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return res;
    }



    /**
     * <p>
     * 通过key向list尾部添加字符串
     * </p>
     *
     * @param key
     * @param strs
     *            可以使一个string 也可以使string数组
     * @return 返回list的value个数
     */
    public Long rpush(String key, String... strs) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = jedisPool.getResource();
            res = jedis.rpush(key, strs);
        } catch (Exception e) {

            LOG.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return res;
    }


    /**
     * <p>
     * 通过key在list指定的位置之前或者之后 添加字符串元素
     * </p>
     *
     * @param key
     * @param where
     *            LIST_POSITION枚举类型
     * @param pivot
     *            list里面的value
     * @param value
     *            添加的value
     * @return
     */
    public Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot,
                        String value) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = jedisPool.getResource();
            res = jedis.linsert(key, where, pivot, value);
        } catch (Exception e) {

            LOG.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return res;
    }






    /**
     * <p>
     * 删除指定的key,也可以传入一个包含key的数组
     * </p>
     *
     * @param keys 一个key 也可以使 string 数组
     * @return 返回删除成功的个数
     */
    public Long del(String... keys) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(keys);
        } catch (Exception e) {

            LOG.error(e.getMessage());
            return 0L;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }





    /**
     * <p>
     * 判断key是否存在
     * </p>
     *
     * @param key
     * @return true OR false
     */
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {

            LOG.error(e.getMessage());
            return false;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }


    /**
     * <p>
     * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
     * </p>
     *
     * @param key
     * @param value
     *            过期时间，单位：秒
     * @return 成功返回1 如果存在 和 发生异常 返回 0
     */
    public Long expire(String key, int value, int indexdb) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            return jedis.expire(key, value);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return 0L;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }








    /**
     * <p>
     * 返回排序后的结果，排序默认以数字作为对象，值被解释为双精度浮点数，然后进行比较。
     * </p>
     *
     * @param key
     * @return 返回列表形式的排序结果
     */
    public List<String> sort(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sort(key);
        } catch (Exception e) {

            LOG.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return null;
    }










}
