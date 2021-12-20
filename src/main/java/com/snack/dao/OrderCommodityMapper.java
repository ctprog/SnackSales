package com.snack.dao;

import com.snack.bean.OrderCommodity;
import com.snack.bean.OrderCommodityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderCommodityMapper {
    long countByExample(OrderCommodityExample example);

    int deleteByExample(OrderCommodityExample example);

    int insert(OrderCommodity record);

    int insertSelective(OrderCommodity record);

    List<OrderCommodity> selectByExample(OrderCommodityExample example);

    int updateByExampleSelective(@Param("record") OrderCommodity record, @Param("example") OrderCommodityExample example);

    int updateByExample(@Param("record") OrderCommodity record, @Param("example") OrderCommodityExample example);
}