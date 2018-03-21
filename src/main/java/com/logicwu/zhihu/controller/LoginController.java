package com.logicwu.zhihu.controller;


import com.logicwu.zhihu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    /**
     * 登录接口
     *
     * */
    @ResponseBody
    @RequestMapping(value = "/login")
    public List<Map> login(Model model, @RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response) {
        List<Map> lists = new ArrayList<>();
        try {

            Map<String, Object> map = userService.login(username, password);
            lists.add(map);
            return lists;


        }catch (Exception e) {
            logger.error("登陆异常" + e.getMessage());
            Map maps = new HashMap();
            maps.put("fail",500);
            lists.add(maps);
            return lists;
        }
    }
}
