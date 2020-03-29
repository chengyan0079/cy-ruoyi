package com.cy.ruoyi.common.redis.util;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import io.lettuce.core.KeyScanCursor;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.ScanCursor;
import io.lettuce.core.api.async.RedisAsyncCommands;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.RedisKeyCommands;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.apache.commons.collections4.IterableUtils.isEmpty;

@SuppressWarnings("unchecked")
public class KeyUtil {

    private static final Log log = LogFactory.get();
    /**
     * 公共方法 按指定分隔符连接字符串
     *
     * @param delimiter 分隔符
     * @param element   待连接元素
     * @return 按指定分隔符连接字符串
     */
    public static String concat(String delimiter, Object... element) {
        if (isNull(element) || element.length == 0) {
            return null;
        }
        return Stream.of(element)
                .filter(Objects::nonNull)
                .map(String::valueOf)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(delimiter));
    }

    public static List<String> scanKeys1(RedisTemplate redisTemplate, String pattern) {
        List<String> keys = (List<String>) redisTemplate.execute(connection -> {
            RedisKeyCommands keyCmds = connection.keyCommands();
            ScanOptions scanOpts = ScanOptions.scanOptions().match(pattern).count(Long.MAX_VALUE).build();
            Cursor<byte[]> cursor = keyCmds.scan(scanOpts);
            Set<String> set = new HashSet<>();
            while (cursor.hasNext()) {
                byte[] bytes = cursor.next();
                set.add(new String(bytes, StandardCharsets.UTF_8));
            }
            return new ArrayList<>(set);
        }, true);
        log.info("SCAN KEYS RETURN {} COUNT", CollectionUtils.isNotEmpty(keys) ? keys.size() : 0);
        return keys;
    }


    public static List<String> scanKeys(RedisTemplate redisTemplate, String pattern) {
        //SCAN 0 MATCH {pattern} COUNT 10000
        return (List<String>) redisTemplate.execute(connection -> {
            //scan 迭代遍历键，返回的结果可能会有重复，需要客户端去重复
            Set<String> redisKeys = new HashSet<>();
            //lettuce 原生api
            RedisAsyncCommands conn = (RedisAsyncCommands) connection.getNativeConnection();
            //游标
            ScanCursor curs = ScanCursor.INITIAL;
            try {
                //采用 SCAN 命令，迭代遍历所有key
                while (!curs.isFinished()) {
                    long count = 10000L;
                    ScanArgs args = ScanArgs.Builder.matches(pattern).limit(count);
                    log.info("SCAN {} MATCH {} COUNT {}", curs.getCursor(), pattern, count);
                    RedisFuture<KeyScanCursor<byte[]>> future = conn.scan(curs, args);
                    KeyScanCursor<byte[]> keyCurs = future.get();
                    List<byte[]> ks = keyCurs.getKeys();
                    Set<String> set = ks.stream().map(bytes -> new String(bytes, StandardCharsets.UTF_8)).collect(Collectors.toSet());
                    log.info("return size:{}", set.size());
                    redisKeys.addAll(set);
                    curs = keyCurs;
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            return new ArrayList<>(redisKeys);
        }, true);
    }

    /**
     * 删除 指定格式的所有 key
     *
     * @param redisTemplate redisTemplate
     * @param pattern       匹配规则
     * @return isDone
     */
    public static boolean delByPattern(RedisTemplate redisTemplate, String pattern) {
        List<String> keys = scanKeys(redisTemplate, pattern);
        if (CollectionUtils.isEmpty(keys)) {
            return true;
        }
        log.info("Del by Pattern: {},total key count:{}", pattern, keys.size());
        Long cnt = redisTemplate.delete(keys);
        return !Long.valueOf(0L).equals(cnt);
    }

    /**
     * 给指定格式的key 设置过期时间
     *
     * @param redisTemplate redisTemplate
     * @param pattern       匹配规则
     * @param timeout       过期时间
     * @param unit          时间单位
     * @return isDone
     */
    public static boolean expireByPattern(RedisTemplate redisTemplate,
                                          final String pattern,
                                          final long timeout,
                                          final TimeUnit unit) {
        List<String> keys = scanKeys(redisTemplate, pattern);
        if (isEmpty(keys)) {
            return false;
        }
        keys.forEach(key -> redisTemplate.expire(key, timeout, unit));
        return true;
    }

//    /**
//     * 在加锁条件下，执行写redis操作
//     *
//     * @param redisTemplate redisTemplate
//     * @param redisKey      redisKey
//     * @param getFromRedis  从redis查询数据
//     * @param setRedis      设置redis
//     * @param <T>           返回数据泛型
//     * @return ret
//     */
//    public static <T> T doWithLock(RedisTemplate redisTemplate,
//                                   String redisKey,
//                                   Supplier<T> getFromRedis,
//                                   Supplier setRedis) {
//        String mutexKey = redisKey + ":MUTEX";
//        String mutexVal = UUID.randomUUID().toString();
//        T ret;
//        try {
//            //获取锁
//            getLockWithExpire(redisTemplate, mutexKey, mutexVal, MIN.getTimeOut(), MIN.getUnit(), -1);
//            //double check
//            T val = getFromRedis.get();
//            if (Objects.nonNull(val)) {
//                if (val instanceof Collection && CollectionUtils.isNotEmpty((Collection) val)) {
//                    //如果redis已经有值，则返回数据
//                    return val;
//                }
//            }
//            //执行写redis操作
//            setRedis.get();
//            ret = getFromRedis.get();
//        } finally {
//            //释放锁
//            releaseLock(redisTemplate, mutexKey, mutexVal);
//        }
//        return ret;
//    }

    /**
     * 尝试在一定等待时间内获取带过期时间的全局锁
     *
     * @param redisTemplate redisTemplate
     * @param mutexKey      锁key，如:{key}:MUTEX
     * @param mutexVal      锁val，如:UUID.randomUUID().toString()
     * @param ttl           锁过期时间
     * @param timeUnit      锁过期时间单位
     * @param waitMilSec    等待毫秒，若 <=0，则一直阻塞等待获得锁
     * @return 是否获取到全局锁
     */
    public static <T> boolean getLockWithExpire(RedisTemplate redisTemplate,
                                                String mutexKey,
                                                String mutexVal,
                                                long ttl,
                                                TimeUnit timeUnit,
                                                long waitMilSec) {
        //获取带有过期时间的全局锁
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        long now = System.currentTimeMillis();
        long end = waitMilSec > 0 ? now + waitMilSec : Long.MAX_VALUE;
        Boolean isSet = false;
        //循环SetNX，直到指定时间或者设置成功
        while (now < end) {
            log.info("SET NX {} {} {} {}", mutexKey, mutexVal, ttl, timeUnit);
            isSet = (Boolean) redisTemplate.execute(
                    redisConn ->
                            redisConn.set(serializer.serialize(mutexKey),
                                    serializer.serialize(mutexVal),
                                    Expiration.from(ttl, timeUnit),
                                    RedisStringCommands.SetOption.SET_IF_ABSENT),
                    true);
            if (Boolean.valueOf(true).equals(isSet)) {
                //设置成功
                log.info("SET NX success");
                break;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            now = System.currentTimeMillis();
        }
        return Boolean.valueOf(true).equals(isSet);
    }

    /**
     * 释放全局锁
     *
     * @param redisTemplate redisTemplate
     * @param mutexKey      锁key
     * @param mutexVal      锁val
     * @return 当前线程是否成功释放全局锁
     */
    public static boolean releaseLock(RedisTemplate redisTemplate,
                                      String mutexKey,
                                      String mutexVal) {
        String lua = "if redis.call('get',KEYS[1]) == ARGV[1] " +
                "then return redis.call('del',KEYS[1]) " +
                "else return 0 end";
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        log.info("if redis.call('get',{}) == {} " +
                "then return redis.call('del',{}) " +
                "else return 0 end", mutexKey, mutexVal, mutexKey);
        Object res = redisTemplate.execute(redisConn -> redisConn.eval(serializer.serialize(lua),
                ReturnType.BOOLEAN,
                1,
                serializer.serialize(mutexKey),
                serializer.serialize(mutexVal)), true);
        log.info("releaseLock:{}", res);
        return Boolean.valueOf(true).equals(res);
    }
}
