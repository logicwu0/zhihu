package com.logicwu.zhihu.controller;


import com.logicwu.zhihu.model.News;
import com.logicwu.zhihu.model.common.ResultData;
import com.logicwu.zhihu.service.CommentService;
import com.logicwu.zhihu.service.NewsService;
import com.logicwu.zhihu.util.ZhihuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    NewsService newsService;

    @Autowired
    CommentService commentService;

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    Jedis jedis = new Jedis("redis://localhost:6379/9");

    @ResponseBody
    @RequestMapping(value = "/comment")
    public ResultData comment() {
        ResultData resultData = new ResultData();

        resultData.setData(jedis);

        return resultData;
    }

    @RequestMapping(value = "/user/addNews", method = {RequestMethod.POST})
    @ResponseBody
    public String addNews(@RequestParam("image") String image,
                          @RequestParam("title") String title,
                          @RequestParam("link") String link){
        try {
            News news = new News();
            news.setCreatedDate(new Date());
            news.setTitle(title);
            news.setLink(link);
            news.setImage(image);
            //if (hostHolder != null){
            //news.setUserId(hostHolder.getUsers().getId());
            //}
            //else {
                //设置一个匿名用户
               news.setUserId(2);
            //}
            newsService.addNews(news);
            return ZhihuUtil.getJSONString(0);
        } catch (Exception e) {
            logger.error("添加资讯失败", e.getMessage());
            return ZhihuUtil.getJSONString(1, "发布失败");
        }
    }

    @RequestMapping(value = "commentList", method = {RequestMethod.GET})
    @ResponseBody
    public ResultData commentList() {
        ResultData resultData = new ResultData();
        try {

            List<Map> commentList = commentService.selectCommentList();
            resultData.setData(commentList);

        } catch (Exception e) {
            logger.error("查找失败" + e.getMessage());
        }
        return resultData;
    }

}
