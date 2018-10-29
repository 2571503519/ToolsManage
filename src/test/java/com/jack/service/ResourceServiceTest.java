package com.jack.service;

import com.jack.config.RootConfig;
import com.jack.dto.ResourceDTO;
import com.jack.pojo.entity.Admin;
import com.jack.pojo.entity.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class ResourceServiceTest {

    private final Logger logger = LoggerFactory.getLogger(ResourceServiceTest.class);

    @Autowired
    private ResourceService rscService;

    @Test
    public void testFindResourceList() {
        Resource resource = new Resource();
        resource.setResPermission("respotery:list");
        List<Resource> resources = rscService.findResourceList(resource);
        if (resources != null && resources.size() > 0) {
            resources.stream().forEach(item -> {
                logger.info("Test: resource = " + item);
            });
        } else {
            logger.info("Test: result is empty");
        }
    }

    @Test
    public void testFindResourcesForAdmin() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        List<ResourceDTO> resourceDTOList = rscService.findResourcesForAdmin(admin);
        logger.info("Test: find resources for admin");
    }

}
