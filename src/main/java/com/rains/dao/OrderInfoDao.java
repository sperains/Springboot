package com.rains.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * Created by Administrator on 2016-08-24.
 */
@Mapper
public interface OrderInfoDao {

    @Select("SELECT Id from  ms_pc_orderinfo  WHERE OrderNum=#{orderNum}")
    String getOrderId(String orderNum);

    @Select("SELECT OrderTime from ms_pc_orderinfo WHERE Id = #{id}")
    Date getOrderTimeById(String id);

    @Select("SELECT PayMoney from ms_pc_orderpayinfo WHERE OrderId = #{id} ")
    Double getPayMoneyById(String id);

    @Update("UPDATE ms_pc_orderinfo SET  weChatId= #{weChatId} where Id=#{orderId}")
    void updateWeChatId(@Param("orderId") String orderId ,@Param("weChatId") String weChartId);
}
