package com.snack.service;

import com.snack.bean.Commodity;

import java.util.List;

public interface CommodityService {
    public Commodity addCommodity(Commodity commodity);
    public List<Commodity> getAllCommodity();
    public List<Commodity> getSomeCommodity(Integer tId);
    public List<Commodity> getCommodityLike(String cName);
    public Commodity getOneCommodity(Integer cId);

    public void deleteCommodity(List<Integer> delIds);

    public void updateCommodityById(Commodity commodity);
}
