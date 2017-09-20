package com.springboot.Service;

import com.springboot.Dao.RunningDao;
import com.springboot.bean.Running;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hardy.wu on 2017/5/3.
 */
@Service
public class RunningService {

    @Autowired
    RunningDao runningDao;

    //保存跑步数据
    public void insertRunning(double distance, double time, double energy, int userId, double speed){
        runningDao.insertRunning(distance, time, energy, new Date(), userId, speed);
    }

    //根据id查询跑步总里程
    public double getSumDistanceById(int userId){
        double sumDistance ;
        List<Running> list_running = runningDao.getRunningById(userId);
        if (list_running.isEmpty()){
            sumDistance = 0.0;
        }
        else {
            sumDistance = runningDao.getSumDistanceById(userId);
        }
        return sumDistance;
    }

    //根据ID查询总里程，平均速度
    public Map getDataById(int userId){
        Map map = new HashMap();
        Running running = runningDao.getDataById(userId);
        map.put("distance", running.getDistance());
        map.put("speed", running.getSpeed());
        return map;
    }

    //展示页面数据
    public Map getLastTimeData(int userId){
        Map map = new HashMap();
        List<Running> list_running = runningDao.getLastTimeData(userId);

        if (list_running.isEmpty()){
            map.put("distance", 0);
            map.put("speed", 0);
            map.put("energy", 0);
            map.put("total_distance", 0);
            map.put("average_speed", 0);
        }
        else {
            Running running = list_running.get(0);
            //查询最近一次运动记录
            map.put("distance", running.getDistance());
            map.put("speed", running.getSpeed());
            map.put("energy", running.getEnergy());

            //根据ID查询总里程，平均速度
            Running running1 = runningDao.getDataById(userId);
            map.put("total_distance", running1.getDistance());
            map.put("average_speed", running1.getSpeed());
        }

        return map;
    }

    //根据id查询用户运动次数
    public int getUserRunCount(int userId){
        int count = runningDao.getUserRunCount(userId);
        return count;
    }

    //根据id查询用户最大运动记录
    public Running getUserMaxRunning(int userId){
        List<Running> list_running = runningDao.getUserMaxRunning(userId);
        return list_running.get(0);
    }

    //根据用户id查询用户运动记录
    public Object getUserRunning (int userId){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Running> list_running = runningDao.getUserRunning(userId);
        Object[] obj = new Object[list_running.size()];
        if (!list_running.isEmpty()){
            for (int i = 0; i<list_running.size(); i++){
                Map map = new HashMap();
                map.put("addTime", formatter.format(list_running.get(i).getAddTime()));
                map.put("distance", list_running.get(i).getDistance());
                map.put("runTime", list_running.get(i).getTime());
                map.put("speed", list_running.get(i).getSpeed());
                obj[i] = map;
            }
        }
        return obj;
    }
}
