package com.logicwu.zhihu.controller;


import com.logicwu.zhihu.model.Question;
import com.logicwu.zhihu.model.common.ResultData;
import com.logicwu.zhihu.service.QuestionService;
import com.logicwu.zhihu.util.ZhihuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class QuestionController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);


    @Autowired
    QuestionService questionService;


    @RequestMapping(value = "/question/add", method = {RequestMethod.POST})
    @ResponseBody
    public ResultData addQuestion(@RequestParam("title") String title, @RequestParam("content") String content) {

        ResultData resultData = new ResultData();

        try {
            Question question = new Question();
            question.setContent(content);
            question.setCreatedDate(new Date());
            question.setTitle(title);

            question.setUserId(1001);

            if(questionService.addQuestion(question) > 0) {
                resultData.setCode("0");
                resultData.setMessage("添加题目成功");
            }

        } catch (Exception e) {
            logger.error("增加题目失败" + e.getMessage());
        }
        return resultData;
    }

    @RequestMapping(value = "/question/search",method = {RequestMethod.GET})
    @ResponseBody
    public ResultData questionList() {

        ResultData resultData = new ResultData();
        //ArrayList list = new ArrayList();

        try {

            List<Map> questionList = questionService.getQuestion();

            resultData.setData(questionList);
            //list.add(questionList);

        } catch (Exception e) {
            logger.error("查找失败" + e.getMessage());
        }

        return resultData;

    }

}
