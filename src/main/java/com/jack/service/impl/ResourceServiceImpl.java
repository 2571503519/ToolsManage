package com.jack.service.impl;

import com.jack.dao.ResourceMapper;
import com.jack.pojo.entity.Resource;
import com.jack.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/24.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper rscMapper;

    @Override
    public List<Resource> findResourceList(Resource resource) {
        List<Resource> resourceList = rscMapper.findResourcesConditionally(resource);

        return (resourceList == null || resourceList.size() < 1)
                ? Collections.EMPTY_LIST : resourceList;
    }

}
