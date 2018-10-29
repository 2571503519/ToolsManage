package com.jack.service.impl;

import com.google.common.collect.Lists;
import com.jack.dao.AdminMapper;
import com.jack.dao.ResourceMapper;
import com.jack.dto.ResourceDTO;
import com.jack.pojo.entity.Admin;
import com.jack.pojo.entity.Resource;
import com.jack.service.ResourceService;
import com.jack.util.Constant;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Resource> findResourceList(Resource resource) {
        List<Resource> resourceList = rscMapper.findResourcesConditionally(resource);

        return (resourceList == null || resourceList.size() < 1)
                ? Collections.EMPTY_LIST : resourceList;
    }

    @Override
    public List<ResourceDTO> findResourcesForAdmin(Admin admin) {
        List<ResourceDTO> resourceDTOList = Lists.newArrayList();

        assemble(0L, resourceDTOList);
        return resourceDTOList;
    }


    /*----------------------------------------私有方法------------------------------------------------------------------*/

    private void assemble(Long resPid, List<ResourceDTO> resourceDTOList) {
        List<Resource> resources = rscMapper.findResourcesByResPid(resPid);
        for (Resource resource : resources) {
            resourceDTOList.add(transferToResourceDTO(resource));
        }
        for (ResourceDTO resourceDTO : resourceDTOList) {
            assemble(resourceDTO.getResId(), resourceDTO.getChildren());
        }
    }

    private ResourceDTO transferToResourceDTO(Resource resource) {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setResId(resource.getResId());
        resourceDTO.setResName(resource.getResName());
        resourceDTO.setResPermission(resource.getResPermission());
        resourceDTO.setGmtCreate(resource.getGmtCreate());
        resourceDTO.setGmtModified(resource.getGmtModified());
        resourceDTO.setChildren(Lists.newArrayList());

        return resourceDTO;
    }



}
