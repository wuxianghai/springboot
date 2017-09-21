package com.hardy.freeTest.service;

import com.hardy.freeTest.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    List<User> selectAllData();

    String returnTest(String str);

    User findUserByUserName(String userName);

}
