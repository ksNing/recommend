package com.example.recommend.controller;

import com.example.recommend.Enum.UserEnum;
import com.example.recommend.dao.SpotRecommendRepository;
import com.example.recommend.dao.SpotRepository;
import com.example.recommend.dao.TupianRepository;
import com.example.recommend.dao.UserRepository;
import com.example.recommend.pojo.Spot;
import com.example.recommend.pojo.SpotRecommend;
import com.example.recommend.pojo.Tupian;
import com.example.recommend.pojo.User;
import com.example.recommend.service.SpotService;
import com.example.recommend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value = "/recommend")
@Slf4j
@CrossOrigin
public class RecommendController {

    final private int RECOMMENDER_NUM = 10;    //推荐结果个数
    final private int PAGE_SIZE = 6;    //推荐结果个数
    @Autowired
    private UserService userService;
    @Autowired
    private SpotService spotService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SpotRepository spotRepository;
    @Autowired
    private SpotRecommendRepository spotRecommendRepository;
    @Autowired
    private TupianRepository tupianRepository;

    @RequestMapping(value = "/userlist")
    public ModelAndView userList(@RequestParam(value = "pageno",defaultValue = "1") Integer currPage,
            ModelAndView modelAndView) {

        modelAndView.setViewName("/userlist");
        PageRequest pageRequest = PageRequest.of(currPage - 1,PAGE_SIZE);
        Page<User> userPage = userService.findAll(pageRequest);
        System.out.println(userPage.getTotalPages());
        modelAndView.addObject("userList",userPage);
        modelAndView.addObject("pageno",currPage);
        return modelAndView;
    }
    @RequestMapping(value = "/recommendation")
    public ModelAndView recommendList(@RequestParam(value = "userId", defaultValue = "2") Integer userId,
                                      ModelAndView modelAndView) {
        List<Spot> spotsRBU = spotService.recommendSpotBasedUser(userId,RECOMMENDER_NUM);
        List<Spot> spotsRBI = spotService.recommendSpotBasedItem(userId,RECOMMENDER_NUM);
        List<Spot> lookedspot = spotService.queryLookedSpotByUser(userId);
        modelAndView.setViewName("/recommendlist");
        modelAndView.addObject("lookedSpot",lookedspot);
        modelAndView.addObject("spotsRBU",spotsRBU);
        modelAndView.addObject("spotsRBI",spotsRBI);
        return modelAndView;
    }

    /*
    用户登录验证
     */
    @RequestMapping(value = "/login")
    public Map<String, String> login(@RequestParam("userName") String userName,
                     @RequestParam("password") String password) {
        HashMap<String,String> map = new HashMap<>();
        User user = userRepository.findByUserName(userName);
        if(password.equals(user.getPassword())) {
            //登录成功
            map.put("code","1");
            map.put("msg", UserEnum.LOGIN_SUCCESS.getMsg());
            System.out.println(map.values());
            return map;
        } else {
            map.put("code","2");
            map.put("msg",UserEnum.LOGIN_ERROR.getMsg());
            System.out.println(map.values());
            return map;
        }
    }

    @RequestMapping(value = "/test")
    public ModelAndView test(ModelAndView modelAndView) {
        modelAndView.setViewName("/test");
        return modelAndView;
    }



    /*
    所有景点信息展示列表
     */
    @GetMapping(value = "/spotlist")
    public List<Tupian> spotList() {
        return tupianRepository.findAll();
    }
    /*
    个人用户信息的展示
     */
    @GetMapping(value = "/myinfo")
    public User myInfo(@RequestParam("userName") String userName) {
        return userRepository.findByUserName(userName);
    }
    /*
    景点推荐结果的展示
     */
    @GetMapping(value = "/recommend_result_list")
    public SpotRecommend spotRecommendList(@RequestParam("spotName") String spotName) {
        SpotRecommend spotRecommend =  spotRecommendRepository.findBySpotName(spotName);
        return spotRecommend;
    }
    /*
    用户注册
     */
    @GetMapping(value = "/register")
    public User register(@RequestParam("userName") String userName,
                         @RequestParam("password") String password,
                         @RequestParam("spotName") String spotName,
                         @RequestParam("touxiangId") String touxiangId){
        User user = new User();
        //user.setUserId(3);
        user.setPassword(password);
        user.setTouxiangId(touxiangId);
        user.setSpotName(spotName);
        user.setUserName(userName);
        return userRepository.save(user);
    }
}
