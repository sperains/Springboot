package com.rains.controller;

import com.rains.service.WftService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Rains on 2016-08-24.
 *
 * 威富通支付入口.
 */
@Controller
public class WftpayController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private WftService wftService;


    /**
     *
     * @param req 请求
     * @param resp 相应
     * @throws IOException
     */
    @RequestMapping("/wftpay")
    public void wtfpay(HttpServletRequest req ,  HttpServletResponse resp) throws IOException {
        System.out.println("wftpay controller");
        String method = req.getParameter("method");
        System.out.println(method);
        if(method.equals("submitOrderInfo")){
            wftService.pay(req, resp);
        }if(method.equals("queryOrder")){
            System.out.println("query");
        }

    }


    @RequestMapping("/success")
    @ResponseBody
    public String paySuccess(){
        logger.info("请求支付成功");
        return "success";
    }

}
