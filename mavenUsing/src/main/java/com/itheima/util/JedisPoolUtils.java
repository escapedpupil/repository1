package com.itheima.util;

import org.omg.CORBA.INTERNAL;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisPoolUtils {
    private static JedisPool jedisPool;
    static {
        try {
            InputStream in = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedisPool.properties");
            Properties p = new Properties();
            p.load(in);
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.parseInt(p.getProperty("maxTotal")));
            config.setMaxIdle(Integer.parseInt(p.getProperty("maxIdle")));
            jedisPool = new JedisPool(config,p.getProperty("host"), Integer.parseInt(p.getProperty("port")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static JedisPool getJedisPool(){
        return jedisPool;
    }
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
