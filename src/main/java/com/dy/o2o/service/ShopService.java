package com.dy.o2o.service;

import com.dy.o2o.dto.ShopExecution;
import com.dy.o2o.entity.Shop;

import java.io.File;

public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg);
}
