package com.dy.o2o.service.impl;

import com.dy.o2o.dao.ShopDao;
import com.dy.o2o.dto.ShopExecution;
import com.dy.o2o.entity.Shop;
import com.dy.o2o.enums.ShopStateEnum;
import com.dy.o2o.exceptions.ShopOperationException;
import com.dy.o2o.service.ShopService;
import com.dy.o2o.util.ImageUtil;
import com.dy.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, File shopImg) {
        //如果为空
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //添加店铺信息
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new ShopOperationException("店铺创建失败");
            } else {
                //添加图片
                if (shopImg != null) {
                    try {
                        addShopImg(shop, shopImg);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }

                }
                //更新图片地址
                effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    throw new ShopOperationException("更新图片地址失败");
                }
            }

        } catch (Exception e) {
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        //待审核
        return new ShopExecution(ShopStateEnum.CHECK, shop);

    }

    private void addShopImg(Shop shop, File shopImg) throws IOException {
        //获取shop图片目录的相对路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        //获得相对存储的位置，dest+文件名和后缀名
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
        //设置shop的图片地址
        shop.setShopImg(shopImgAddr);
    }
}
