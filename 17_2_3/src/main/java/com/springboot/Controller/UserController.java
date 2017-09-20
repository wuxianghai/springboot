package com.springboot.Controller;

import com.springboot.Service.UserService;
import com.springboot.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hardy.wu on 2017/5/3.
 */
@RestController
@SpringBootApplication
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/insertUser")
    public Map<String, Object> insert_user(int userId, String nick_name, String sex, int age, int height, int weight, String character, String insterest, String mobile, String password){
        Map<String, Object> map = new HashMap<>();
        //注册新用户
        if (userId == 0 ){
        int count = userService.getUser(nick_name, mobile);
        if (count == 0){
            userService.insertUser(nick_name, sex, age, height, weight, character, insterest, mobile, password);
            map.put("code", 0);
            map.put("msg", "注册成功");
        }
        if (count == 1) {
            map.put("code", 1);
            map.put("msg", "昵称已存在");
        }
        if (count == 2) {
            map.put("code", 1);
            map.put("msg", "手机号已注册");
        }
        }
        else {
            userService.updateUser(userId, nick_name, sex, age, height, weight, character, insterest, mobile, password);
            map.put("code", 11);
            map.put("msg", "更新成功");
        }
        return map;
    }

    @RequestMapping("/userLogin")
    public Map<String, Object> userLogin(String nick_name, String password){
        Map<String, Object> map = new HashMap<>();
        int count = userService.userLogin(nick_name, password);
        if (count == 2){
            map.put("code", 1);
            map.put("msg", "用户名不存在");
        }
        if (count == 0){
            Map date = userService.getUserIdByLogin(nick_name,password);
            map.put("data", date);
            map.put("code", 0);
            map.put("msg", "登陆成功");
        }
        if (count == 1){
            map.put("code", 1);
            map.put("msg", "用户名或密码错误");
        }
        return map;
    }

    //编辑用户信息
    @RequestMapping("/getUserInfor")
    public Map getUserInfor(int userId){
        Map map = new HashMap();
        User user = userService.getUserInfor(userId);
        map.put("data", user);
        map.put("code", 10);
        map.put("msg", "获取成功");
        return map;
    }
}
