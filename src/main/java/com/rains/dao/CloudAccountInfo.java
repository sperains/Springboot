package com.rains.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * Created by Rains
 * on 2016-08-25.
 */
@Mapper
public interface CloudAccountInfo {

    @Select("SELECT  mch_id, mch_key from  MS_PC_CLOUDACCOUNTINFO  where Id = #{cloudId}")
    Map<String , Object> getWftInfoByCloudId(String cloudId);
}
