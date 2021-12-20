package com.snack.dao;

import com.snack.bean.Img;
import com.snack.bean.ImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImgMapper {
    long countByExample(ImgExample example);

    int deleteByExample(ImgExample example);

    int insert(List<Img> imgs);

    int insertSelective(Img record);

    List<Img> selectByExample(ImgExample example);

    int updateByExampleSelective(@Param("record") Img record, @Param("example") ImgExample example);

    int updateByExample(@Param("record") Img record, @Param("example") ImgExample example);
}