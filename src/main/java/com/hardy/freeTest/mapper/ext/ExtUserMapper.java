package com.hardy.freeTest.mapper.ext;

import com.hardy.freeTest.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExtUserMapper {

    User findByUserName(String username);
}