package com.rains.dao;

import com.rains.entity.Food;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2016-08-22.
 */
@Mapper
public interface FoodDao {

    List<Food> findAll();
}
