package com.snack.business.service.impl;

import com.snack.business.bean.Commodity;
import com.snack.business.bean.Img;
import com.snack.business.bean.ImgExample;
import com.snack.business.bean.User;
import com.snack.business.dao.ImgMapper;
import com.snack.business.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImgServiceImpl implements ImgService {

    @Autowired
    ImgMapper imgMapper;

    @Override
    public void addImgs(Commodity commodity, MultipartFile[] imgs, String imageSavePath){
        List<Img> imgList = new ArrayList<>();
        for (MultipartFile img : imgs) {
            Img imgBean = new Img();
            imgBean.setcId(commodity.getcId());
            String filename = img.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf("."));
            String substring = UUID.randomUUID().toString().substring(0, 15);
            filename = substring + suffix;
            String finalPath = imageSavePath + File.separator + filename;
            try {
                img.transferTo(new File(finalPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            imgBean.setiName(filename);
            imgList.add(imgBean);
        }
        imgMapper.insert(imgList);
    }

    @Override
    public void deleteImgs(List<Integer> cIds,String imagePath){
        if (cIds.size()==0){
            return;
        }
        ImgExample ex = new ImgExample();
        ImgExample.Criteria cr = ex.createCriteria();
        cr.andCIdIn(cIds);
        List<Img> imgList = imgMapper.selectByExample(ex);
        for (Img img : imgList) {
            String iName = img.getiName();
            String finalPath = imagePath + File.separator + iName;
            File file = new File(finalPath);
            file.delete();
        }
        imgMapper.deleteByExample(ex);
    }

    @Override
    public void deleteImgByName(String imagePath,String iName) {
        String finalPath = imagePath + File.separator + iName;
        File file = new File(finalPath);
        file.delete();
        ImgExample ex = new ImgExample();
        ImgExample.Criteria cr = ex.createCriteria();
        cr.andINameEqualTo(iName);
        imgMapper.deleteByExample(ex);
    }

    @Override
    public User addUserHead(User user,MultipartFile img, String imageSavePath) {
        String filename = img.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        String substring = UUID.randomUUID().toString().substring(0, 15);
        filename = substring + suffix;
        String finalPath = imageSavePath + File.separator + filename;
        try {
            img.transferTo(new File(finalPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setuHead(filename);
        return user;
    }

}
