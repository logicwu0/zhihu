package com.logicwu.zhihu.controller;


import com.logicwu.zhihu.model.User;
import com.logicwu.zhihu.model.common.ResultData;
import com.logicwu.zhihu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;
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
    public List<Map> login( @RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response) {
        List<Map> lists = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        try {
            Map<String, Object> map = userService.login(username, password);
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                //if (rememberme) {
                    //cookie.setMaxAge(3600*24*5);
                //}
                response.addCookie(cookie);
                /*
                eventProducer.fireEvent(new EventModel(EventType.LOGIN)
                        .setExt("username", username).setExt("email", "zjuyxy@qq.com")
                        .setActorId((int)map.get("userId")));

                if (StringUtils.isNotBlank(next)) {
                    return "redirect:" + next;
                }
                return "redirect:/";
            } else {
            */
                map1.put("msg", map.get("msg"));
                lists.add(map);
                return lists;
            }

        } catch (Exception e) {
            logger.error("登陆异常" + e.getMessage());
            return lists;
        }
        return lists;
    }

    /**
     * 注册接口
     *
     * */
    @ResponseBody
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public ResultData reg(@RequestBody User user,
                          HttpServletResponse response) {
        Map<String, Object> map1 = new HashMap<>();
        ResultData resultData = new ResultData();
        try {
            Map<String, Object> map = userService.register(user.getName(), user.getPassword());
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                response.addCookie(cookie);

                resultData.setMessage("注册成功");
            }
            else{
                map1.put("msg", map.get("msg"));
                resultData.setMessage("注册失败");
                resultData.setData(map1);
            }

        } catch (Exception e) {
            logger.error("注册异常" + e.getMessage());
            map1.put("msg", "服务器错误");
            resultData.setMessage("注册失败");
            resultData.setData(map1);
        }
        return resultData;
    }

}
