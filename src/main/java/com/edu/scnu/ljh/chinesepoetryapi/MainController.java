package com.edu.scnu.ljh.chinesepoetryapi;

import com.alibaba.fastjson.JSON;
import com.edu.scnu.ljh.chinesepoetryapi.dbmodel.Poem;
import com.edu.scnu.ljh.chinesepoetryapi.dbmodel.Poetry;
import com.edu.scnu.ljh.chinesepoetryapi.dbmodel.PoetryAuthor;
import com.edu.scnu.ljh.chinesepoetryapi.services.PoemServiceImpl;
import com.edu.scnu.ljh.chinesepoetryapi.services.PoetryAuthorServiceImpl;
import com.edu.scnu.ljh.chinesepoetryapi.services.PoetryServiceImpl;
import com.github.houbb.opencc4j.util.ZhConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Random;

@Controller
public class MainController {

    @Autowired
    private PoetryAuthorServiceImpl poetryAuthorService;

    @Autowired
    private PoetryServiceImpl poetryService;

    @Autowired
    private PoemServiceImpl poemService;

    @RequestMapping(value = "api/poem", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getPoem(String id, String title,
                          @RequestHeader(value = "order", required = false) Integer order,
                          HttpServletResponse response) {
        List<Poem> list;
        if (order != null) {
            response.addHeader("order", order.toString());
        }
        if (id != null) {
            list = poemService.queryAllPoemById(id);
        } else if (title != null) {
            list = poemService.queryAllPoemByTitle(title);
        } else {
            int rand_id = new Random(System.currentTimeMillis()).nextInt(21047) + 1;
            list = poemService.queryAllPoemById(String.valueOf(rand_id));
        }
        return JSON.toJSONString(list);
    }


    @RequestMapping(value = "api/poetry-author", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getPoetryAuthor(String id, String name,
                                  @RequestHeader(value = "order", required = false) Integer order,
                                  HttpServletResponse response) {
        List<PoetryAuthor> list = null;
        if (order != null) {
            response.addHeader("order", order.toString());
        }
        if (id != null) {
            list = poetryAuthorService.queryAllPoetryAuthorById(id);
        } else if (name != null) {
            name = ZhConverterUtil.convertToTraditional(name);
            list = poetryAuthorService.queryAllPoetryAuthorByName(name);
        } else {
            //随机推荐
            int rand_id = new Random(System.currentTimeMillis()).nextInt(100) + 1;
            list = poetryAuthorService.queryAllPoetryAuthorById(String.valueOf(rand_id));
        }
        return JSON.toJSONString(list);
    }

    @RequestMapping(value = "api/poetry", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getPoetry(String id, String title, String author_name,
                            String author_id, @RequestHeader(value = "order", required = false) Integer order,
                            HttpServletResponse response) {
        List<Poetry> list;
        if (order != null) {
            response.addHeader("order", order.toString());
        }
        if (id != null) {
            list = poetryService.queryAllPoetryById(id);
        } else if (title != null) {
            title = ZhConverterUtil.convertToTraditional(title);
            list = poetryService.queryAllPoetryByTitle(title);
        } else if (author_name != null) {
            author_name = ZhConverterUtil.convertToTraditional(author_name);
            list = poetryService.queryAllPoetryByAuthor_Name(author_name);
        } else if (author_id != null) {
            list = poetryService.queryAllPoetryByAuthor_Id(author_id);
        } else {
            //随机推荐
            int rand_id = new Random(System.currentTimeMillis()).nextInt(311827) + 1;
            list = poetryService.queryAllPoetryById(String.valueOf(rand_id));
        }
        return JSON.toJSONString(list);
    }
}
