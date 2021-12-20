package com.snack.service.impl;

import com.snack.bean.Commodity;
import com.snack.bean.CommodityExample;
import com.snack.bean.Img;
import com.snack.bean.ImgExample;
import com.snack.dao.CommodityMapper;
import com.snack.dao.ImgMapper;
import com.snack.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityMapper commodityMapper;


    @Override
    public Commodity addCommodity(Commodity commodity) {
        commodityMapper.insert(commodity);
        return commodity;
    }

    @Override
    public List<Commodity> getAllCommodity() {
        return commodityMapper.selectByExample(null);
    }

    @Override
    public List<Commodity> getSomeCommodity(Integer tId) {
        CommodityExample example = new CommodityExample();
        CommodityExample.Criteria criteria = example.createCriteria();
        criteria.andTIdEqualTo(tId);
        return commodityMapper.selectByExample(example);
    }

    @Override
    public Commodity getOneCommodity(Integer cId) {
       return commodityMapper.selectByPrimaryKey(cId);
    }

    @Override
    public List<Commodity> getCommodityLike(String cName){
        return commodityMapper.selectCommodityLike(cName);
    }

    @Override
    public void deleteCommodity(List<Integer> delIds) {
        if (delIds.size()==0){
            return;
        }
        CommodityExample example = new CommodityExample();
        CommodityExample.Criteria criteria = example.createCriteria();
        criteria.andCIdIn(delIds);
        commodityMapper.deleteByExample(example);
    }

    @Override
    public void updateCommodityById(Commodity commodity) {
        commodityMapper.updateByPrimaryKey(commodity);
    }

}
