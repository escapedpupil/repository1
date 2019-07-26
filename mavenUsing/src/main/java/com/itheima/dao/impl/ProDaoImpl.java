package com.itheima.dao.impl;

import com.itheima.dao.ProDao;
import com.itheima.domain.Pro;
import com.itheima.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProDaoImpl implements ProDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDs());
    @Override
    public List<Pro> findAll() {
        String sql = "SELECT * FROM province";
        return template.query(sql,new BeanPropertyRowMapper<>(Pro.class));
    }
}
