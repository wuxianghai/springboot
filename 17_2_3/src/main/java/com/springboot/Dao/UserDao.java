package com.springboot.Dao;

import com.springboot.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Hardy.wu on 2017/5/3.
 */
@Mapper
public interface UserDao {


    @Select("select nick_name from s_user")
    String getNickName();

    //根据昵称查用户
    @Select("select count(*) from s_user su where su.nick_name = #{0}")
    int getUserByNickName(String nick_name);

    //根据手机查用户
    @Select("select count(*) from s_user su where su.mobile = #{0}")
    int getUserByMobile(String mobile);

    @Insert("insert into s_user (nick_name, sex, age, height, weight, user_character, insterest, mobile, password) value( #{0}, #{1}, #{2}, #{3}, #{4}, #{5}, #{6}, #{7}, #{8}) ")
//            " value (#{nick_name}, #{sex}, #{age}, #{height}, #{weight}, #{character}, #{insterest}, #{mobile}, #{password})")
    void insert_user(String nick_name, String sex, int age, int height, int weight, String character, String insterest, String mobile, String password);

    //验证用户登录密码
    @Select(" select count(*) from s_user su where su.nick_name = #{0} and su.password = #{1}")
    int userLogin(String nick_name, String password);

    //根据用户名密码查询用户id
    @Select(" select su.id from s_user su where su.nick_name = #{0} and su.password = #{1}")
    int getUserIdByLogin(String nick_name, String password);

    //根据id查昵称
    @Select(" select su.nick_name from s_user su where su.id = #{0}")
    String getUserNickName(int userId);

    //根据ID查用户信息
    @Select(" select * from s_user su where su.id = #{0}")
    List<User> getUserInfor(int userId);

    @Update("update  s_user set nick_name = #{1}, sex= #{2}, age= #{3}, height= #{4}, weight= #{5}, user_character= #{6}, insterest= #{7}, mobile= #{8}, password= #{9} where id = #{0}")
    void updata_user( int userId, String nick_name, String sex, int age, int height, int weight, String character, String insterest, String mobile, String password);

}
