package com.snack.business.service.impl;

import com.snack.business.bean.CommodityExample;
import com.snack.business.bean.CommodityType;
import com.snack.business.dao.CommodityMapper;
import com.snack.business.dao.CommodityTypeMapper;
import com.snack.business.service.CommodityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityTypeServiceImpl implements CommodityTypeService {
    @Autowired
    private CommodityTypeMapper commodityTypeMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public List<CommodityType> getComTypes(){
        List<CommodityType> cTypes = commodityTypeMapper.selectByExample(null);
        for (CommodityType cType : cTypes) {
            CommodityExample example = new CommodityExample();
            CommodityExample.Criteria criteria = example.createCriteria();
            criteria.andTIdEqualTo(cType.gettId());
            long num = commodityMapper.countByExample(example);
            cType.setcNum(num);
        }
        return cTypes;
    }

    @Override
    public void saveComType(CommodityType commodityType) {
        commodityTypeMapper.insert(commodityType);
    }

    @Override
    public void updateComType(CommodityType commodityType) {
        commodityTypeMapper.updateByPrimaryKey(commodityType);
    }

    @Override
    public void deleteType(Integer tId) {
        commodityTypeMapper.deleteByPrimaryKey(tId);
    }
}
