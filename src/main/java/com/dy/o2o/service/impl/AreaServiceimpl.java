package com.dy.o2o.service.impl;

import com.dy.o2o.dao.AreaDao;
import com.dy.o2o.entity.Area;
import com.dy.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceimpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
