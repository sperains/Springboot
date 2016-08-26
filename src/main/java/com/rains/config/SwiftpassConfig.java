package com.rains.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <一句话功能简述>
 * <功能详细描述>配置信息
 * 
 * @author  Administrator
 * @version  [版本号, 2014-8-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
@ConfigurationProperties( locations = "classpath:application-wft.properties")
public class SwiftpassConfig{

	/**
     * 美食微信平台APPID
     */
    private String appid;

    private String key;

    private String mch_id;

    private String req_url;

    private String notify_url;

    private String callback_url;

    private String service;

    private String version;

    private String charset;

    private String sign_type;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getReq_url() {
        return req_url;
    }

    public void setReq_url(String req_url) {
        this.req_url = req_url;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public static String appid2;

    @Override
    public String toString() {
        return "SwiftpassConfig{" +
                "appid='" + appid + '\'' +
                ", key='" + key + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", req_url='" + req_url + '\'' +
                ", notify_url='" + notify_url + '\'' +
                ", callback_url='" + callback_url + '\'' +
                ", service='" + service + '\'' +
                ", version='" + version + '\'' +
                ", charset='" + charset + '\'' +
                ", sign_type='" + sign_type + '\'' +
                '}';
    }

}
