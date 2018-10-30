package com.jack.service;

import com.github.pagehelper.PageInfo;
import com.jack.dto.ResourceDTO;
import com.jack.pojo.entity.Admin;
import com.jack.pojo.entity.Resource;
import com.jack.util.PageQuery;

import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/24.
 */
public interface ResourceService {

    PageInfo<Resource> findResourceList(PageQuery pageQuery, Resource resource);

    List<ResourceDTO> findResources();

}
