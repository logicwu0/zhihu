package com.logicwu.zhihu.controller;


import com.logicwu.zhihu.dao.QuestionDAO;
import com.logicwu.zhihu.model.Question;
import com.logicwu.zhihu.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionDAO questionDAO;

    @ResponseBody
    @RequestMapping(value = "/hello",method = RequestMethod.GET)

    public String hello() {
        return "hello world";
    }

    @ResponseBody
    @RequestMapping(value = "/helloq",method = RequestMethod.GET)

    public List helloq(@RequestParam("id") int id) {

        List<Question> list = new ArrayList<>();
        Question q1 = questionDAO.getById(id);
        list.add(q1);
        return list;
    }
}
