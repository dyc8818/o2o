package com.dy.o2o.controller.shopAdmin;

import com.dy.o2o.dto.ShopExecution;
import com.dy.o2o.entity.PersonInfo;
import com.dy.o2o.entity.Shop;
import com.dy.o2o.enums.ShopStateEnum;
import com.dy.o2o.service.ShopService;
import com.dy.o2o.util.HttpServletRequestUtil;
import com.dy.o2o.util.ImageUtil;
import com.dy.o2o.util.PathUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    ShopService shopService;

    @RequestMapping(value = "registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        //1.接收并转化参数，包括图片信息和店铺信息
        Map<String, Object> modelmap = new HashMap<>();
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        //使用jackson dataBind用于读取json中的对象
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelmap.put("success", false);
            modelmap.put("errMsg", e.getMessage());
            return modelmap;
        }

        //接收图片
        CommonsMultipartFile shopImg = null;
        //创建解析器
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //解析器判断是不是multipart的
        if (commonsMultipartResolver.isMultipart(request)) {
            //如果是的化，转换为MultipartHttpServletRequest
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelmap.put("success", false);
            modelmap.put("errMsg", "上传图片不能为空");
            return modelmap;
        }
        //2.注册店铺
        if (shop != null && shopImg != null) {
            PersonInfo owner = new PersonInfo();
            owner.setUserId(1L);
            shop.setOwner(owner);

//            //将创建一个新的文件
//            File file = new File(PathUtil.getImgBasePath() + ImageUtil.getRandomFileName());
//            try {
//                //创建
//                file.createNewFile();
//            } catch (IOException e) {
//                modelmap.put("success", false);
//                modelmap.put("errMsg", e.getMessage());
//                return modelmap;
//            }
//            try {
//                //写入文件
//                inputStreamToFile(shopImg.getInputStream(), file);
//            } catch (IOException e) {
//                modelmap.put("success", false);
//                modelmap.put("errMsg", e.getMessage());
//                return modelmap;
//            }
            ShopExecution shopExecution = null;
            try {
                shopExecution = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                //如果添加成功
                if (shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
                    modelmap.put("success", true);
                } else {
                    modelmap.put("success", false);
                    modelmap.put("errorMsg", shopExecution.getStateInfo());
                }
            } catch (IOException e) {
                modelmap.put("success", false);
                modelmap.put("errMsg", e.getMessage());
                return modelmap;
            }

            return modelmap;

        } else {
            modelmap.put("success", false);
            modelmap.put("errMsg", "请输入店铺信息");
            return modelmap;
        }
    }

//    private static void inputStreamToFile(InputStream inputStream, File file) {
//        OutputStream outputStream = null;
//        try {
//            outputStream = new FileOutputStream(file);
//            int bufferRead = 0;
//            byte[] buffer = new byte[1024];
//            while ((bufferRead = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bufferRead);
//            }
//
//
//        } catch (IOException e) {
//            throw new RuntimeException("调用inputStreamToFile异常：" + e.getMessage());
//        } finally {
//            try {
//                if (inputStream != null) inputStream.close();
//                if (outputStream != null) outputStream.close();
//            } catch (IOException e) {
//                throw new RuntimeException("调用inputStreamToFile关闭IO流异常：" + e.getMessage());
//            }
//        }
//    }
}
