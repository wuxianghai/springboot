package com.hardy.freeTest.controller;

import com.hardy.freeTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping("/getData")
    public Map<String, Object> getData(){
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("查询结果：", userService.selectAllData());
        }catch (Exception e ){
            System.out.println("错误信息:" + e.getMessage());
        }
        return map;
    }
}
