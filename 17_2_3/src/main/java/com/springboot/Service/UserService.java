package com.springboot.Service;

import com.springboot.Dao.UserDao;
import com.springboot.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hardy.wu on 2017/5/3.
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public String getNickName(){
        return  userDao.getNickName();
    }

    public void insertUser(String nick_name, String sex, int age, int height, int weight, String character, String insterest, String mobile, String password){
        userDao.insert_user(nick_name, sex, age, height, weight, character, insterest, mobile, password);
    }

    /**
    * @description: 注册时判断昵称，电话是否存在
    * @author hardy.wu
    * @Date 2017/5/3 17:08
    * @param
    */
    public int getUser(String nick_name, String mobile){
        //昵称已存在
        int count_name = userDao.getUserByNickName(nick_name);
        if (count_name > 0){
            return 1;
        }
        //手机号重复
        int count_mobile = userDao.getUserByMobile(mobile);
        if (count_mobile > 0){
            return 2;
        }
        return 0;
    }

    public int userLogin(String nick_name, String password){
        int count_name = userDao.getUserByNickName(nick_name);
        //用户名不存在
        if (count_name == 0){
            return 2;
        }
        int count_user = userDao.userLogin(nick_name, password);
        //登陆成功
        if (count_user == 1) {
            return 0;
        }
        //用户名或密码错误
        else {
            return 1;
        }
    }

    //登陆时获取用户ID
    public Map getUserIdByLogin(String nick_name, String password){
        Map map = new HashMap();
        int id = userDao.getUserIdByLogin(nick_name, password);
        map.put("user_id", id);
        return map;
    }

    //根据id查询用户昵称
    public String getUserNickName(int userId){
        String nick_name = userDao.getUserNickName(userId);
        return nick_name;
    }

    //根据id查询用户信息
    public User getUserInfor(int userId){
        List<User> list_user = userDao.getUserInfor(userId);
        return list_user.get(0);
    }

    //编辑更新保存数据
    public void updateUser(int userId, String nick_name, String sex, int age, int height, int weight, String character, String insterest, String mobile, String password){
        userDao.updata_user(userId, nick_name, sex, age, height, weight, character, insterest, mobile, password);
    }
}
