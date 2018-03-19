package com.logicwu.zhihu.service;


import com.logicwu.zhihu.dao.QuestionDAO;
import com.logicwu.zhihu.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;

    public Question getById(int id) {
        return questionDAO.getById(id);
    }
}
