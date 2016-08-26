package com.rains.dao;

import com.rains.entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016-08-22.
 */
@Repository
public class FoodDaoByTemplate {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Food> findAll(){
        String sql = "select FoodName name FROM ms_pc_foodinfo";

        return jdbcTemplate.query(sql, new RowMapper<Food>() {
            @Override
            public Food mapRow(ResultSet rs, int i) throws SQLException {
                Food food = new Food();
                food.setName(rs.getString("name"));
                return food;
            }
        });
    }
}
