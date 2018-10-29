package com.jack.service;

import com.jack.dto.ResourceDTO;
import com.jack.pojo.entity.Admin;
import com.jack.pojo.entity.Resource;

import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/24.
 */
public interface ResourceService {

    List<Resource> findResourceList(Resource resource);

    List<ResourceDTO> findResourcesForAdmin(Admin admin);

}
