package com.dy.o2o.dao;

import com.dy.o2o.BaseTest;
import com.dy.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    ShopCategoryDao shopCategoryDao;
    @Test
    public void  testQueryShopCategory(){
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setPriority(2);
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(shopCategory);
        System.out.println(shopCategoryList.get(0).getShopCategoryName());
    }

}