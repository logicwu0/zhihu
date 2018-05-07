package com.logicwu.zhihu.service;


import com.logicwu.zhihu.dao.QuestionDAO;
import com.logicwu.zhihu.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;

    public Question getById(int id) {
        return questionDAO.getById(id);
    }

    public int addQuestion(Question question) {
        question.setTitle(question.getTitle());
        question.setContent(question.getContent());
        // 敏感词过滤
        question.setTitle(question.getTitle());
        question.setContent(question.getContent());
        return questionDAO.addQuestion(question) > 0 ? question.getId() : 0;
    }

    public int updateCommentCount(int id, int count) {
        return questionDAO.updateCommentCount(id, count);
    }

    public List getQuestion() {
        return questionDAO.getQuestion();
    }

}
