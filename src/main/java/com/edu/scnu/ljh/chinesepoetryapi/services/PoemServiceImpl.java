package com.edu.scnu.ljh.chinesepoetryapi.services;

import com.edu.scnu.ljh.chinesepoetryapi.dbmodel.Poem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PoemServiceImpl {
    @Resource
    private JdbcTemplate jdbcTemplate;

    private List<Poem> _queryAllPoem(String sql, boolean toGetContent) {
        List<Poem> list = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Poem pa = new Poem();
            pa.setId(rs.getInt("id"));
            pa.setAuthor_id(rs.getInt("author_id"));
            pa.setAuthor(rs.getString("author"));
            if (toGetContent) {
                pa.setContent(rs.getString("content"));
            }
            pa.setTitle(rs.getString("title"));

            return pa;
        });
        //返回结果
        return list;
    }

    public List<Poem> queryAllPoemByTitle(String title) {
        //SQL
        String sql = "SELECT id,author_id,title,author FROM poems WHERE title LIKE \'" + title + "%\'";
        //结果
        List<Poem> list = _queryAllPoem(sql, false);
        //返回结果
        return list;
    }

    public List<Poem> queryAllPoemById(String id) {
        //SQL
        String sql = "SELECT * FROM poems WHERE id=" + id;
        //结果
        List<Poem> list = _queryAllPoem(sql, true);
        //返回结果
        return list;
    }
}
