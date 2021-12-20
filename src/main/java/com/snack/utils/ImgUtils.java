package com.snack.utils;

public class ImgUtils {
    public static boolean imgValid(String imgName){
        String suffix = imgName.substring(imgName.lastIndexOf("."));
        String[] strings = {".jpg",".jpeg",".png"};
        for (String string : strings) {
            if(string.equals(suffix)){
                return true;
            }
        }
        return false;
    }
}
