package com.itheima.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.dao.ProDao;
import com.itheima.dao.impl.ProDaoImpl;
import com.itheima.domain.Pro;
import com.itheima.service.ProService;
import com.itheima.util.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProServiceImpl implements ProService {
    private ProDao proDao = new ProDaoImpl();
    @Override
    public String findProToJson() {
        Jedis jedis = null;
        String proJson = null;
        try{
            jedis = JedisPoolUtils.getJedis();
            proJson = jedis.get("province");
        }catch (Exception e){
            e.printStackTrace();
        }
        if(proJson == null || proJson.trim().length() == 0){
            //查询数据库
            List<Pro> list = proDao.findAll();
            ObjectMapper mapper = new ObjectMapper();
            try {
                proJson = mapper.writeValueAsString(list);
                jedis.set("province",proJson);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        if(jedis != null){
            jedis.close();
        }
        return proJson;
    }
}
