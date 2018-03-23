package com.logicwu.zhihu.controller;


import com.logicwu.zhihu.dao.LoginTicketDAO;
import com.logicwu.zhihu.dao.QuestionDAO;
import com.logicwu.zhihu.model.LoginTicket;
import com.logicwu.zhihu.model.Question;
import com.logicwu.zhihu.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionDAO questionDAO;
    @Autowired
    LoginTicketDAO loginTicketDAO;

    @ResponseBody
    @RequestMapping(value = "/hello",method = RequestMethod.GET)

    public String hello() {
        return "hello world";
    }

    @ResponseBody
    @RequestMapping(value = "/helloq")

    public List helloq(@RequestParam("id") int id,@RequestParam("token") String token) {

        List<Map> list = new ArrayList<>();
        List<LoginTicket> list1 = new ArrayList<>();
        Question q1 = questionDAO.getById(id);
        LoginTicket l1 = loginTicketDAO.selectByTicket(token);
        list.add((Map) q1);
        list1.add(l1);
        return list1;
    }
}
