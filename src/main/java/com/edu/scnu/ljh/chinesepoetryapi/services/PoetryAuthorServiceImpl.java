package com.edu.scnu.ljh.chinesepoetryapi.services;

import com.edu.scnu.ljh.chinesepoetryapi.dbmodel.PoetryAuthor;
import com.github.houbb.opencc4j.util.ZhConverterUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PoetryAuthorServiceImpl {

    @Resource
    private JdbcTemplate jdbcTemplate;

    private List<PoetryAuthor> _queryAllPoetryAuthor(String sql) {
        return _queryAllPoetryAuthor(sql, false);
    }

    private List<PoetryAuthor> _queryAllPoetryAuthor(String sql, boolean toGetIntroduce) {
        List<PoetryAuthor> list = jdbcTemplate.query(sql, (rs, rowNum) -> {
            PoetryAuthor pa = new PoetryAuthor();
            pa.setId(rs.getInt("id"));
            pa.setName(ZhConverterUtil.convertToSimple(rs.getString("name")));
            if (toGetIntroduce) {
                pa.setIntro(ZhConverterUtil.convertToSimple(rs.getString("intro")));
            }
            if (rs.getString("dynasty").equals("T")) {
                pa.setDynasty("唐");
            } else {
                pa.setDynasty("宋");
            }

            return pa;
        });
        //返回结果
        return list;
    }

    public List<PoetryAuthor> queryAllPoetryAuthorById(String id) {
        //SQL
        String sql = "SELECT * FROM poetry_author WHERE id=" + id;
        //结果
        List<PoetryAuthor> list = _queryAllPoetryAuthor(sql, true);
        return list;
    }

    public List<PoetryAuthor> queryAllPoetryAuthorByName(String name) {
        //SQL
        String sql = "SELECT * FROM poetry_author WHERE name LIKE \'" + name + "%\'";
        //结果
        List<PoetryAuthor> list = _queryAllPoetryAuthor(sql);
        return list;
    }

}
