package com.rains.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-08-24.
 */
public class PayInfo implements Serializable {

    private String orderNum;

    private String openId;

    private String cloudId;

    private String cloudName;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCloudId() {
        return cloudId;
    }

    public void setCloudId(String cloudId) {
        this.cloudId = cloudId;
    }

    public String getCloudName() {
        return cloudName;
    }

    public void setCloudName(String cloudName) {
        this.cloudName = cloudName;
    }

    @Override
    public String toString() {
        return "PayInfo{" +
                "orderNum='" + orderNum + '\'' +
                ", openId='" + openId + '\'' +
                ", cloudId='" + cloudId + '\'' +
                ", cloudName='" + cloudName + '\'' +
                '}';
    }
}
