package com.dy.o2o.dao;

import com.dy.o2o.BaseTest;
import com.dy.o2o.entity.Area;
import com.dy.o2o.entity.PersonInfo;
import com.dy.o2o.entity.Shop;
import com.dy.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

    @Test
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopDesc("我是update");
        shop.setShopImg("我是update");
        shop.setShopAddr("和平西");
        shop.setCreateTime(new Date());
        shop.setShopId(2L);
        int effectNum = shopDao.updateShop(shop);
        System.out.println(effectNum);
    }
    @Test
    public void testInsertShop(){
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
        shop.setShopName("测试的店铺");
        shop.setPhone("test");
        shop.setAdvice("test");
        shop.setEnableStatus(1);
        shop.setPriority(1);
        shop.setShopDesc("test");
        shop.setShopImg("TEST");
        shop.setShopAddr("和平北路");
        shop.setCreateTime(new Date());
        int effectNum = shopDao.insertShop(shop);
        System.out.println(effectNum);
    }



}