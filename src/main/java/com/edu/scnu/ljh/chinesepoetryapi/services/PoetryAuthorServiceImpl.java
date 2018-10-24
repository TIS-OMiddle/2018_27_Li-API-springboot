package com.edu.scnu.ljh.chinesepoetryapi.services;

import com.edu.scnu.ljh.chinesepoetryapi.dbmodel.PoetryAuthor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.util.List;

@Service
public class PoetryAuthorServiceImpl {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<PoetryAuthor> queryAllPoetryAuthor(String name) {
        //SQL
        String sql = "SELECT * FROM poetry_author WHERE name LIKE \'%"+name+"%\'";
        //结果
        List<PoetryAuthor> list = jdbcTemplate.query(sql, (rs, rowNum) -> {
            PoetryAuthor pa=new PoetryAuthor();
            pa.setId(rs.getInt("id"));
            pa.setName(rs.getString("name"));
            pa.setIntro(rs.getString("intro"));
            pa.setDynasty(rs.getString("dynasty"));

            return pa;
        });
        //返回结果
        return list;
    }

}
