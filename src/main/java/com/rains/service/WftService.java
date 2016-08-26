package com.rains.service;

import com.rains.entity.PayInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016-08-24.
 */
public interface WftService {

    public void pay(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
