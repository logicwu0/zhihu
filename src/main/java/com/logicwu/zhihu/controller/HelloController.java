package com.logicwu.zhihu.controller;


import com.logicwu.zhihu.dao.LoginTicketDAO;
import com.logicwu.zhihu.dao.QuestionDAO;
import com.logicwu.zhihu.model.LoginTicket;
import com.logicwu.zhihu.model.Question;
import com.logicwu.zhihu.model.common.ResultData;
import com.logicwu.zhihu.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    public ResultData helloq(@RequestParam("id") int id, @RequestParam("token") String token) {

        ResultData resultData = new ResultData();
        Map map = new HashMap();
        Date d = new Date();
        Question q1 = questionDAO.getById(id);
        LoginTicket l1 = loginTicketDAO.selectByTicket(token);
        if (l1.getExpired().before(d)) {
            int del = loginTicketDAO.deleteTicket(token);
            resultData.setData(del);
            resultData.setMessage("身份过期，请重新登陆");
        } else {
            try {
                map.put("question", q1);
                map.put("ticket", l1);
                resultData.setData(map);
                resultData.setMessage("登陆成功");
                return resultData;
            } catch (Exception e) {
                resultData.setMessage("登陆失败");
            }
            return resultData;
        }
        return resultData;
    }
}
