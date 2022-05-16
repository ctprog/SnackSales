package com.snack.business.dao;

import com.snack.business.bean.ShoppingCar;
import com.snack.business.bean.ShoppingCarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShoppingCarMapper {
    long countByExample(ShoppingCarExample example);

    int deleteByExample(ShoppingCarExample example);

    int deleteByPrimaryKey(Integer sId);

    int insert(ShoppingCar record);

    int insertSelective(ShoppingCar record);

    List<ShoppingCar> selectByExample(ShoppingCarExample example);

    ShoppingCar selectByPrimaryKey(Integer sId);

    int updateByExampleSelective(@Param("record") ShoppingCar record, @Param("example") ShoppingCarExample example);

    int updateByExample(@Param("record") ShoppingCar record, @Param("example") ShoppingCarExample example);

    int updateByPrimaryKeySelective(ShoppingCar record);

    int updateByPrimaryKey(ShoppingCar record);
}