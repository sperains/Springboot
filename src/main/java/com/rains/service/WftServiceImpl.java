package com.rains.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rains.config.SwiftpassConfig;
import com.rains.dao.CloudAccountInfo;
import com.rains.dao.OrderInfoDao;
import com.rains.util.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Rains
 * on 2016-08-24.
 */
@Service
public class WftServiceImpl implements WftService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Autowired
    private CloudAccountInfo accountInfoDao;

    @Autowired
    private SwiftpassConfig wftconfig;

   @Override
    public void pay(HttpServletRequest req, HttpServletResponse resp) throws IOException {

       SortedMap<String, String> map = XmlUtils.getParameterMap(req);
       logger.info(map.toString());

       logger.info(wftconfig.toString());

       String orderNum = map.get("orderNum");
       String openid = map.get("openId");
       String cloudId = map.get("cloudId");
       String cloudName = map.get("cloudName");


        String out_trade_no= "";    //商户订单号

        String message = "";
        String mch_id = "";
        String key = "";


        String orderId = orderInfoDao.getOrderId(orderNum);
        logger.info("订单Id:" + orderId);


        if(orderId == null){
            message = "订单不存在" ;
            resp.getWriter().write(message);
            return ;
        }


        Date orderTime = orderInfoDao.getOrderTimeById(orderId);
        logger.info("下单时间:" + orderTime);


        if(TimeUtil.isOverdue(orderTime)){
            String overTime = TimeUtil.getOverTime(orderTime);
            message = "订单超时:" + overTime ;
            resp.getWriter().write(message);
            return ;
        }

       //生成商户系统内部订单号 ,对于数据库中的WeChatId
       out_trade_no = Order.getOutTradeNo();

       orderInfoDao.updateWeChatId(orderId , out_trade_no);


       Double payMoney = orderInfoDao.getPayMoneyById(orderId);

       Map<String, Object> wftInfo = accountInfoDao.getWftInfoByCloudId(cloudId);


       if(wftInfo!=null && wftInfo.get("mch_id") != null && !"".equals(wftInfo.get("mch_id")) &&   wftInfo.get("mch_key")!=null){
            mch_id = wftInfo.get("mch_id").toString();
            key = wftInfo.get("mch_key").toString();
       }else{
           mch_id = wftconfig.getMch_id();
           key = wftconfig.getKey();
       }

       logger.info("mch_id :" + mch_id + " ,key:" + key);
       map.clear();

       map.put("is_raw","0");// 是微信原生支付接口
       map.put("service", wftconfig.getService());
       map.put("version", wftconfig.getVersion());
       map.put("charset", wftconfig.getCharset());
       map.put("sign_type", wftconfig.getSign_type());

       map.put("mch_id", mch_id);
       // 通知地址
       map.put("notify_url", wftconfig.getNotify_url());
       map.put("appid", wftconfig.getAppid());

       map.put("callback_url", wftconfig.getCallback_url());

       map.put("nonce_str", String.valueOf(new Date().getTime()));

       // 设备号	device_info	否	String(32)	013467007045764	终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
       map.put("device_info", "WEB"); //终端设备号

       // 商品描述
       map.put("body", "美食快点商品描述"); //商品描述
       // 商品详情
       map.put("detail", "商品名称明细列表"); //商品名称明细列表

       String attach = "{cloudId:" + cloudId + ",cloudName:" + cloudName +"}";

       // 附加数据  attach 否 String(127) 深圳分店 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
       map.put("attach", attach); //商品名称明细列表
       // 商户订单号
       map.put("out_trade_no", out_trade_no); //商家订单号
       // 总金额
       map.put("total_fee",(int)(payMoney * 100) + ""); //商品金额,以分为单位
       // 终端IP
       map.put("spbill_create_ip",req.getRemoteAddr()); //订单生成的机器IP，指用户浏览器端IP

       map.put("mch_create_ip",req.getRemoteAddr());
       // 交易类型
       map.put("trade_type", "JSAPI"); //交易类型
       // 用户标识
       // map.put("openid", openid);
       map.put("sub_openid", openid);

       logger.info("过滤前:" + map);

       //过滤参数
       Map<String ,String> params = SignUtils.paraFilter(map);
       logger.info("过滤后:" + params);


       //生成签名原始字符串 ,需对参数进行ascii进行排序组装成querystring格式
       StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
       SignUtils.buildPayParams(buf, params, false);

       //生成签名
       String sign = MD5.sign(buf.toString(), "&key=" + key, "utf-8");
       map.put("sign", sign);

       logger.info("签名" + map.get("sign"));

       String reqUrl = wftconfig.getReq_url();

       logger.info("请求参数\n" + XmlUtils.parseXML(map));


       Map<String, String> resultMap = null;
       HttpPost httpPost = new HttpPost(reqUrl);
       CloseableHttpResponse response = null;
       CloseableHttpClient client = null;

       StringEntity paramEntity = new StringEntity(XmlUtils.parseXML(map) , "utf-8");
       //设置头信息
       httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
       //设置参数
       httpPost.setEntity(paramEntity);

       client = HttpClients.createDefault();
       response = client.execute(httpPost);


       if(response != null && response.getEntity() != null){
           try {
               resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()) , "utf-8");

               message = XmlUtils.toXml(resultMap);

               if (!SignUtils.checkParam(resultMap, key)) {
                   message = "验证签名不通过";
                   logger.info(message);
                   return ;
               }
               if("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))){
                    String code_img_url =  resultMap.get("code_img_url");
                    logger.info(code_img_url);
                    message = "ok";
                    ObjectMapper mapper = new ObjectMapper();
                    resp.getWriter().write("1fsdf");
               }


           } catch (Exception e) {
               e.printStackTrace();
           }
       }


   }
}
