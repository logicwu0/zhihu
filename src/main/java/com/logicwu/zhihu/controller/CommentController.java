package com.logicwu.zhihu.controller;


import com.logicwu.zhihu.model.common.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

@Controller
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    Jedis jedis = new Jedis("redis://localhost:6379/9");

    @ResponseBody
    @RequestMapping(value = "/comment")
    public ResultData comment() {
        ResultData resultData = new ResultData();

        resultData.setData(jedis);

        return resultData;
    }

}
