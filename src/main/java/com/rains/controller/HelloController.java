package com.rains.controller;

import com.rains.dao.FoodDao;
import com.rains.dao.FoodDaoByTemplate;
import com.rains.dao.UserDao;
import com.rains.entity.File;
import com.rains.entity.RainsTest;
import com.rains.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016-08-22.
 */


@Controller
@ConfigurationProperties(prefix = "person")
public class HelloController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private FoodDao foodDao;

    @Autowired
    private FoodDaoByTemplate foodDaoByTemplate;

    private String firstname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    @Value("${my.secret}")
    private String secret;

    @Value("${my.uuid}")
    private String uuid;

    @Autowired
    private RainsTest rainsTest;


    /*@Autowired
    private OSSClient ossClient;*/

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/")
    @ResponseBody
    @Transactional
    public String sayHello(){
        logger.info("slf4j info");
        logger.warn("slf4j warn");
        logger.error("slf4j error");


        System.out.println(secret);
        System.out.println(uuid);
        System.out.println(uuid);
        System.out.println(firstname);

        System.out.println(rainsTest);

        System.out.println(userDao.findAll());
        System.out.println(foodDao.findAll());

        System.out.println(foodDaoByTemplate.findAll());

        userDao.deleteById("bc72e7a5-37d4-4c4f-8619-039084a11e1b");

        userDao.deleteById("9b028c2e-66ed-4a91-975e-c0df772595fa");

        logger.debug("日志输出测试 Debug");
        System.out.println("123123");
        System.out.println("123123");
        System.out.println("123123");
        System.out.println("123123");

        return "你好 rains";
    }

    @RequestMapping("/user/{id}")
    @ResponseBody
    public String test(@PathVariable("id") String id){
        System.out.println(id);
        return id;
    }

    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @RequestMapping("/fileupload")
    public String upload(File file){
        System.out.println(file);
        return "success";
    }

    @RequestMapping("/entity")
    public String entity (User user) throws Exception {
        throw new Exception("发生错误");

    }






}
