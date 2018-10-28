package com.edu.scnu.ljh.chinesepoetryapi;

import com.alibaba.fastjson.JSON;
import com.edu.scnu.ljh.chinesepoetryapi.dbmodel.Poetry;
import com.edu.scnu.ljh.chinesepoetryapi.dbmodel.PoetryAuthor;
import com.edu.scnu.ljh.chinesepoetryapi.services.PoetryAuthorServiceImpl;
import com.edu.scnu.ljh.chinesepoetryapi.services.PoetryServiceImpl;
import com.github.houbb.opencc4j.util.ZhConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;

@Controller
public class MainController {

    @Autowired
    private PoetryAuthorServiceImpl poetryAuthorService;

    @Autowired
    private PoetryServiceImpl poetryService;

    @RequestMapping(value = "api/poetry-author", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getPoetryAuthor(String id, String name) {
        List<PoetryAuthor> list = null;
        if (id != null) {
            list = poetryAuthorService.queryAllPoetryAuthorById(id);
        } else if (name != null) {
            name = ZhConverterUtil.convertToTraditional(name);
            list = poetryAuthorService.queryAllPoetryAuthorByName(name);
        }
        return JSON.toJSONString(list);
    }

    @RequestMapping(value = "api/poetry", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getPoetry(String id, String title, String author_name, String author_id) {
        List<Poetry> list;
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
