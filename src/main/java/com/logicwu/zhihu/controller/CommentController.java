package com.logicwu.zhihu.controller;


import com.logicwu.zhihu.model.Comment;
import com.logicwu.zhihu.model.EntityType;
import com.logicwu.zhihu.model.common.ResultData;
import com.logicwu.zhihu.service.CommentService;
import com.logicwu.zhihu.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    CommentService commentService;
    @Autowired
    QuestionService questionService;

    @ResponseBody
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public ResultData addComment(@RequestBody int questionId, @RequestBody String content) {

        ResultData resultData = new ResultData();

        try {

            Comment comment = new Comment();
            comment.setContent(content);
            comment.setCreatedDate(new Date());
            comment.setEntityType(EntityType.ENTITY_QUESTION);
            comment.setEntityId(questionId);
            commentService.addComment(comment);

            int count = commentService.getCommentCount(comment.getEntityId(), comment.getEntityType());
            questionService.updateCommentCount(comment.getEntityId(), count);

            resultData.setData(comment);
            resultData.setMessage("添加评论成功");
            return resultData;

        } catch (Exception e) {
            logger.error("添加评论失败" + e.getMessage());
        }
        return resultData;
    }
}
