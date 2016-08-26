package com.rains.dao;

import com.rains.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * Created by Administrator on 2016-08-22.
 */
@Mapper
public interface UserDao {

    List<User> findAll();

    int deleteById(String id);
}
