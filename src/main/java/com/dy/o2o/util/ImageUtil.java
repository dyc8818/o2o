package com.dy.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();

    public static void generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File newFile = new File(PathUtil.getImgBasePath() + relativeAddr);

        try {
            //将收到的数据裁剪到200*200，并添加水印
            Thumbnails.of(thumbnail.getInputStream()).size(200, 200).
                    watermark(Positions.TOP_RIGHT, ImageIO.read(new File(PathUtil.basePath + "/img/waterMark.jpg")), 0.5f).
                    outputQuality(0.5f).toFile(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建目标路径的目录
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath()+targetAddr;
        File dirPath = new File(realFileParentPath);
        if(!dirPath.exists()){
            //递归创建文件夹
            dirPath.mkdirs();
        }
    }

    /**
     * 获取扩展名
     * @return
     * @param thumbnail
     */
    private static String getFileExtension(CommonsMultipartFile thumbnail) {
        String originName = thumbnail.getName();
        return originName.substring(originName.lastIndexOf("."));
    }


    /**
     * 生成随机文件名，当前年月日小时分钟秒+五位随机数
     * @param
     * @throws IOException
     */
    private static String getRandomFileName() {
        int random = r.nextInt(89999)+10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr+random;
    }


    public static void main(String[] args) throws IOException {

        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(basePath);
        Thumbnails.of(new File(PathUtil.getImgBasePath() + "pic.jpg")).size(200, 200)
                .watermark(Positions.TOP_RIGHT, ImageIO.read(new File(basePath + "/img/waterMark.jpg")), 0.5f).
                outputQuality(0.5f).toFile(PathUtil.getImgBasePath() + "pic2.jpg");
    }
}
