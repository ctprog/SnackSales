package com.snack.service;

import com.snack.bean.CommodityType;

import java.util.List;

public interface CommodityTypeService {
    public List<CommodityType> getComTypes();

    void saveComType(CommodityType commodityType);

    void updateComType(CommodityType commodityType);

    void deleteType(Integer tId);
}
