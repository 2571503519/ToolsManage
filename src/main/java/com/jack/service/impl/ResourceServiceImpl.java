package com.jack.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.jack.dao.AdminMapper;
import com.jack.dao.ResourceMapper;
import com.jack.dto.ResourceDTO;
import com.jack.pojo.entity.Admin;
import com.jack.pojo.entity.Resource;
import com.jack.service.ResourceService;
import com.jack.util.Constant;
import com.jack.util.PageQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
    public PageInfo<Resource> findResourceList(PageQuery pageQuery, Resource resource) {
        PageHelper.startPage(pageQuery.getPage(), pageQuery.getPageSize());
        List<Resource> resourceList = rscMapper.findResourcesConditionally(resource);

        resourceList = (resourceList == null || resourceList.size() < 1) ? Collections.EMPTY_LIST : resourceList;
        PageInfo<Resource> pageInfo = new PageInfo<>(resourceList);
        return pageInfo;
    }

    @Override
    public List<ResourceDTO> findResources() {
        List<ResourceDTO> resourceDTOList = Lists.newArrayList();

        assembleByResPid(0L, resourceDTOList);
        return resourceDTOList;
    }


    /*----------------------------------------私有方法------------------------------------------------------------------*/

    /**
     * 获取指定resPid下的所有资源（直接和间接）
     * @param resPid
     * @param resourceDTOList
     */
    private void assembleByResPid(Long resPid, List<ResourceDTO> resourceDTOList) {
        List<Resource> resources = rscMapper.findResourcesByResPid(resPid);
        for (Resource resource : resources) {
            resourceDTOList.add(transferToResourceDTO(resource));
        }
        for (ResourceDTO resourceDTO : resourceDTOList) {
            assembleByResPid(resourceDTO.getResId(), resourceDTO.getChildren());
        }
    }

    /**
     * 将Resource转化为ResourceDTO
     * @param resource
     * @return
     */
    private ResourceDTO transferToResourceDTO(Resource resource) {
        ResourceDTO resourceDTO = new ResourceDTO();
        BeanUtils.copyProperties(resource, resourceDTO);
        resourceDTO.setChildren(Lists.newArrayList());
//        ResourceDTO resourceDTO = new ResourceDTO();
//        resourceDTO.setResId(resource.getResId());
//        resourceDTO.setResName(resource.getResName());
//        resourceDTO.setResUrl(resource.getResUrl());
//        resourceDTO.setResPermission(resource.getResPermission());
//        resourceDTO.setGmtCreate(resource.getGmtCreate());
//        resourceDTO.setGmtModified(resource.getGmtModified());

        return resourceDTO;
    }


}
