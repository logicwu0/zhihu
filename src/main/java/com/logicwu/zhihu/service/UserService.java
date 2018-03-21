package com.logicwu.zhihu.service;


import com.logicwu.zhihu.dao.UserDAO;
import com.logicwu.zhihu.model.User;
import com.logicwu.zhihu.util.ZhihuUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {


    @Autowired
    private UserDAO userDAO;

    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<>();

        if (null == username) {
            map.put("msg", "用户名不能为空");
            return map;
        }
        /*
        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }
        User user = userDAO.selectByName(username);

        if (user == null) {
            map.put("msg", "用户名不存在");
            return map;
        }

        if (!ZhihuUtil.MD5(password+user.getSalt()).equals(user.getPassword())) {
            map.put("msg", "密码不正确");
            return map;
        }
        */
        User user = userDAO.selectByName(username);
        //String ticket = addLoginTicket(user.getId());
        //map.put("ticket", ticket);
        map.put("message",200);
        map.put("userId", user.getId());
        return map;
    }

}