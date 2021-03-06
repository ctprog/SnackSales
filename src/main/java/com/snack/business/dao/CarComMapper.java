package com.snack.business.dao;

import com.snack.business.bean.CarCom;
import com.snack.business.bean.CarComExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CarComMapper {
    long countByExample(CarComExample example);

    int deleteByExample(CarComExample example);

    int insert(CarCom record);

    int insertSelective(CarCom record);

    List<CarCom> selectByExample(CarComExample example);

    int updateByExampleSelective(@Param("record") CarCom record, @Param("example") CarComExample example);

    int updateByExample(@Param("record") CarCom record, @Param("example") CarComExample example);
}