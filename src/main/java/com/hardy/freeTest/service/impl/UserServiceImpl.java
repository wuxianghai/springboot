package com.hardy.freeTest.service.impl;

import com.hardy.freeTest.bean.User;
import com.hardy.freeTest.bean.UserExample;
import com.hardy.freeTest.mapper.UserMapper;
import com.hardy.freeTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAllData(){
        List<User> list_user = new ArrayList<>();
        UserExample example = new UserExample();
        list_user = userMapper.selectByExample(example);
        return list_user;
    }

    @Override
    public String returnTest(String str){
        return  "返回值：" +str;
    }

    /**
     * 根据姓名查找用户信息
     * @param userName
     * @return
     */
    @Override
    public User findUserByUserName(String userName){
        UserExample example = new UserExample();
        UserExample.Criteria criteria =example.createCriteria();
        criteria.andUsernameEqualTo(userName);
        List<User> user = userMapper.selectByExample(example);
        return  user.get(0);
    }
}
