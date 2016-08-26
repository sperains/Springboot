package com.rains.controller;

import com.rains.entity.PayInfo;
import com.rains.service.WftService;
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

    @Autowired
    private WftService wftService;


    /**
     *
     * @param req
     * @param resp
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

}
