package com.snack.service;

import com.snack.bean.Commodity;
import com.snack.bean.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImgService {
    public void addImgs(Commodity commodity, MultipartFile[] imgs, String imageSavePath);
    public void deleteImgs(List<Integer> cIds,String imagePath);

    public void deleteImgByName(String imagePath,String iName);
    public User addUserHead(User user, MultipartFile img, String imageSavePath);
}
