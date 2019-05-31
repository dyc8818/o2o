package com.dy.o2o.util;

public class PathUtil {
    static String separator = System.getProperty("file.separator");
    static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if(os.toLowerCase().startsWith("win")){
            basePath = "D:/project/img/";
        }else{
            basePath = "/home/dy/linux/linux/WorkSpace/java/o2o/img/";
        }
        basePath = basePath.replace("/",separator);
        return basePath;
    }
    public static String getShopImagePath(long shopId){
        String imagePath = "/home/dy/linux/linux/WorkSpace/java/o2o/upload/item/shop/"+shopId+"/";
        return imagePath.replace("/",separator);
    }
}
