package com.edu.scnu.ljh.chinesepoetryapi.services;

import com.edu.scnu.ljh.chinesepoetryapi.dbmodel.Poetry;
import com.edu.scnu.ljh.chinesepoetryapi.dbmodel.PoetryAuthor;
import com.github.houbb.opencc4j.util.ZhConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PoetryServiceImpl {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PoetryAuthorServiceImpl poetryAuthorService;

    private List<Poetry> _queryAllPoetry(String sql, boolean toGetContent) {
        List<Poetry> list = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Poetry pa = new Poetry();
            pa.setId(rs.getInt("id"));
            pa.setAuthor_id(rs.getInt("author_id"));
            pa.setAuthor(ZhConverterUtil.convertToSimple(rs.getString("author")));
            if (toGetContent){
                pa.setContent(ZhConverterUtil.convertToSimple(rs.getString("content")));
                pa.setYunlv_rule(rs.getString("yunlv_rule"));
            }
            pa.setTitle(ZhConverterUtil.convertToSimple(rs.getString("title")));
            pa.setDynasty(rs.getString("dynasty"));

            return pa;
        });
        //返回结果
        return list;
    }

    private List<Poetry> _queryAllPoetry(String sql) {
        return _queryAllPoetry(sql, false);
    }

    public List<Poetry> queryAllPoetryByTitle(String title) {
        //SQL
        String sql = "SELECT id,author_id,title,author,dynasty FROM poetry WHERE title LIKE \'" + title + "%\'";
        //结果
        List<Poetry> list = _queryAllPoetry(sql);
        //返回结果
        return list;
    }

    public List<Poetry> queryAllPoetryById(String id) {
        //SQL
        String sql = "SELECT * FROM poetry WHERE id=" + id;
        //结果
        List<Poetry> list = _queryAllPoetry(sql, true);
        //返回结果
        return list;
    }

    public List<Poetry> queryAllPoetryByAuthor_Id(String author_id) {
        //SQL
        String sql = "SELECT * FROM poetry WHERE author_id=" + author_id;
        //结果
        List<Poetry> list = _queryAllPoetry(sql);
        //返回结果
        return list;
    }

    public List<Poetry> queryAllPoetryByAuthor_Name(String author_name) {
        List<Poetry> list = null;
        List<PoetryAuthor> temp = poetryAuthorService.queryAllPoetryAuthorByName(author_name);
        if (!temp.isEmpty()) {
            String sql = "SELECT * FROM poetry WHERE author_id=" + temp.get(0).getId();
            list = _queryAllPoetry(sql);
        }
        //SQL
        return list;
    }
}
