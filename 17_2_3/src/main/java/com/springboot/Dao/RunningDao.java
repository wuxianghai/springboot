package com.springboot.Dao;

import com.springboot.bean.Running;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by Hardy.wu on 2017/5/3.
 */
@Mapper
public interface RunningDao {


    //保存跑步数据
    @Insert(" insert into user_running (distance, time, energy, addTime, userId, speed) value(#{0}, #{1}, #{2}, #{3}, #{4}, #{5}) ")
    void insertRunning(double distance, double time, double energy, Date addTime, int userId, double speed);

    //根据id查询跑步总里程
    @Select(" select SUM(distance) from user_running where userId = #{0}")
    double getSumDistanceById(int userId);

    //根据ID查询总里程，平均速度
    @Select(" select SUM(distance) as distance, AVG(speed) as speed from " +
            " user_running where userId= #{0}")
    Running getDataById(int userId);

    //查询最近一次运动记录
    @Select(" select * from user_running where userId = #{0} order by addTime desc ")
    List<Running> getLastTimeData(int userID);

    //根据id查是否有运动记录
    @Select(" select * from user_running where userId = #{0}")
    List<Running> getRunningById(int userId);

    //根据id查用户运动次数
    @Select(" select count(*) from user_running where userId = #{0}")
    int getUserRunCount(int userId);

    //查询用户最大运动距离数据
    @Select(" select distance, time from user_running where userId = #{0} ORDER BY distance DESC")
    List<Running> getUserMaxRunning(int userId);

    //根据用户id查询运动数据
    @Select(" select * from user_running where userId = #{0}")
    List<Running> getUserRunning(int userId);
}
