package com.dy.o2o.service.impl;

import com.dy.o2o.BaseTest;
import com.dy.o2o.entity.Area;
import com.dy.o2o.service.AreaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class AreaServiceImplTest extends BaseTest {
    @Autowired
    AreaService areaService;

    @Test
    public void getAreaList() {
        List<Area> list = areaService.getAreaList();
        System.out.println(list.get(0).getAreaName());
    }
}