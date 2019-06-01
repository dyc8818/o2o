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

    public static String generateThumbnail(File thumbnail, String targetAddr) throws IOException {
        //生成随机名称
        String realFileName = getRandomFileName();
        //获取扩展名
        String extension = getFileExtension(thumbnail);
        //生成目标目录
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File newFile = new File(PathUtil.getImgBasePath() + relativeAddr);
        System.out.println(thumbnail.getPath());
        System.out.println(newFile.getPath());

        File thumbnailFile = new File(PathUtil.basePath + "img/waterMark.jpg");
        System.out.println(PathUtil.basePath + "img/waterMark.jpg");
        //将收到的数据裁剪到200*200，并添加水印，设置0.5的透明度和0.5的质量
        Thumbnails.of(thumbnail).size(200, 200).
                watermark(Positions.TOP_RIGHT, ImageIO.read(thumbnailFile), 0.5f).
                outputQuality(0.5f).toFile(newFile);

        return relativeAddr;

    }

    public static void generateThumbnail(String souceImgPath, String targetAddr) {
        //生成随机名称
        String realFileName = getRandomFileName();
        //得到扩展名
        String extension = getFileExtension(souceImgPath);
        //生成目标目录
        makeDirPath(targetAddr);
        //得到路径
        String relativeAddr = targetAddr + realFileName + extension;
        //得到最终路径
        File newFile = new File(PathUtil.getImgBasePath() + relativeAddr);

        try {
            //将收到的数据裁剪到200*200，并添加水印，设置0.5的透明度和0.5的质量
            Thumbnails.of(new File(souceImgPath)).size(200, 200).
                    watermark(Positions.TOP_RIGHT, ImageIO.read(new File(PathUtil.basePath + "/img/waterMark.jpg")), 0.5f).
                    outputQuality(0.5f).toFile(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建目标路径的目录
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            //递归创建文件夹
            dirPath.mkdirs();
        }
    }

    /**
     * 获取扩展名
     *
     * @param thumbnail
     * @return
     */
    public static String getFileExtension(File thumbnail) {
        String originName = thumbnail.getName();
        return originName.substring(originName.lastIndexOf("."));
    }

    public static String getFileExtension(String file) {
        return file.substring(file.lastIndexOf("."));
    }

    /**
     * 生成随机文件名，当前年月日小时分钟秒+五位随机数
     *
     * @param
     * @throws IOException
     */
    public static String getRandomFileName() {
        int random = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + random;
    }


    public static void main(String[] args) throws IOException {

        generateThumbnail(PathUtil.getImgBasePath() + "pic.jpg", "thumbnail/");
//        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        System.out.println(basePath);
//        Thumbnails.of(new File(PathUtil.getImgBasePath() + "pic.jpg")).size(200, 200)
//                .watermark(Positions.TOP_RIGHT, ImageIO.read(new File(basePath + "/img/waterMark.jpg")), 0.5f).
//                outputQuality(0.5f).toFile(PathUtil.getImgBasePath() + "pic2.jpg");
    }
}
