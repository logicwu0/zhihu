package com.logicwu.zhihu.controller;


import com.logicwu.zhihu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
                model.addAttribute("msg", map.get("msg"));
                lists.add(map);
                return lists;
            }

        } catch (Exception e) {
            logger.error("登陆异常" + e.getMessage());
            return lists;
        }
        return lists;
    }
}
