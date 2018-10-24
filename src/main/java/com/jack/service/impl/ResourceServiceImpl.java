package com.jack.service.impl;

import com.jack.dao.ResourceMapper;
import com.jack.pojo.entity.Resource;
import com.jack.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/24.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> findResourceList() {

        return null;
    }

}
