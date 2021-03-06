package com.dy.o2o.service.impl;

import com.dy.o2o.BaseTest;
import com.dy.o2o.dto.ShopExecution;
import com.dy.o2o.entity.Area;
import com.dy.o2o.entity.PersonInfo;
import com.dy.o2o.entity.Shop;
import com.dy.o2o.entity.ShopCategory;
import com.dy.o2o.enums.ShopStateEnum;
import com.dy.o2o.service.ShopService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import static org.junit.Assert.*;

public class ShopServiceImplTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺3");
        shop.setPhone("test3");
        shop.setAdvice("test3 ");
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setPriority(1);
        shop.setShopDesc("test3");
        shop.setShopAddr("和平路");
        File shopImg = new File("/home/dy/linux/linux/WorkSpace/java/o2o/img/pic.jpg");
        ShopExecution shopExecution = shopService.addShop(shop, new FileInputStream(shopImg),shopImg.getName());
        assertEquals(ShopStateEnum.CHECK.getState(),shopExecution.getState());

    }

}