package com.springboot.Controller;

import com.springboot.Service.RunningService;
import com.springboot.Service.UserService;
import com.springboot.bean.Running;
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
@RequestMapping("/running")
public class RunningController {

    @Autowired
    RunningService runningService;

    @Autowired
    UserService userService;

    //保存跑步数据
    @RequestMapping("/insertRunning")
    public Map insertRunning(double distance, double time, double energy, int userId, double speed) throws Exception{
        Map<String, Object> map = new HashMap();
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");//小写的mm表示的是分钟
//        Date date = sdf.parse(time);
        runningService.insertRunning(distance, time, energy, userId, speed);

        map.put("data","");
        map.put("code",0);
        map.put("msg", "保存成功");
        return map;
    }

    //根据id查询跑步总里程
    @RequestMapping("/getSumDistance")
    public Map getSumDistance(int userId){
        Map map = new HashMap();
        double distance = runningService.getSumDistanceById(userId);
        map.put("data", distance);
        map.put("code", 0);
        map.put("msg", "获取成功");
        return map;
    }

//    //根据ID查询总里程，平均速度
//    @RequestMapping("/getDataById")
//    public Map getDataById(int userId){
//        Map map = new HashMap();
//        Map map1 = runningService.getDataById(userId);
//        map.put("data",map1);
//        map.put("code", 0);
//        map.put("msg", "获取成功");
//        return map;
//    }

    //展示页面数据
    @RequestMapping("/getLastTimeData")
    public Map getLastTimeData(int userId){
        Map map = new HashMap();

        Map map1 = runningService.getLastTimeData(userId);

        map.put("data", map1);
        map.put("code", 0);
        map.put("msg", "获取成功");

        return map;
    }

    //我的页面数据展示
    @RequestMapping("/getMineAllData")
    public Map getMineAllData(int userId){
        Map map = new HashMap();
        map.put("code",0);
        map.put("msg","获取成功");

        String nick_name = userService.getUserNickName(userId);
        map.put("nickName",nick_name);

        int run_count = runningService.getUserRunCount(userId);

        if (run_count == 0){
            map.put("runCount", 0);
            map.put("distance", 0);
            map.put("runTime", 0);
        }
        else {
            map.put("runCount", run_count);

            Running running = runningService.getUserMaxRunning(userId);
            map.put("distance", running.getDistance());
            map.put("runTime", running.getTime());
        }
        return map;
    }

    //运动历史记录
    @RequestMapping("/getRunningHistory")
    public Map getRunningHistory(int userId){
        Map map = new HashMap();
        Object objData = runningService.getUserRunning(userId);
        map.put("data", objData);
        map.put("code", 0);
        map.put("msg", "获取成功");

        return map;
    }
}
