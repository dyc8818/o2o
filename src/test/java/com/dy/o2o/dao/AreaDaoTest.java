package com.dy.o2o.dao;

import com.dy.o2o.BaseTest;
import com.dy.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea(){
        List<Area> areaList = areaDao.queryArea();
        System.out.println(areaList.get(0).getAreaName());
//        ApplicationContext a = new ClassPathXmlApplicationContext("classpath:spring/spring-dao.xml");
//        AreaDao areaDao =a.getBean(AreaDao.class);
//        List<Area> areaList1 =areaDao.queryArea();
//        System.out.println(areaList1.size());
        //assertEquals(3,areaList.size());
    }

}