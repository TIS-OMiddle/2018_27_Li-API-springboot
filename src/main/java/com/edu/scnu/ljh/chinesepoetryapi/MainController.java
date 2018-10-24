package com.edu.scnu.ljh.chinesepoetryapi;

import com.alibaba.fastjson.JSON;
import com.edu.scnu.ljh.chinesepoetryapi.dbmodel.PoetryAuthor;
import com.edu.scnu.ljh.chinesepoetryapi.services.PoetryAuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PoetryAuthorServiceImpl poetryAuthorService;

    @RequestMapping("api/poetryauthor")
    @ResponseBody
    public String getPoetryAuthor(String name){
        List<PoetryAuthor>list=poetryAuthorService.queryAllPoetryAuthor(name);
        return JSON.toJSONString(list);
    }
}
