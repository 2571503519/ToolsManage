package com.jack.dao;

import com.jack.dao.admin.ResourceMapper;
import com.jack.pojo.entity.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

/**
 * Created by Jackaroo Zhang on 2018/10/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class ResourceMapperTest {
    private final Logger logger = LoggerFactory.getLogger(ResourceMapperTest.class);

    @Autowired
    private ResourceMapper mapper;

    @Test
    public void testSaveResource() {
        Random random = new Random(System.currentTimeMillis());
        Resource resource = new Resource();
        resource.setResName("库房管理");
        resource.setResPid(0L);
        resource.setResUrl("/api/v" + random.nextInt(10) +"/repertory");
        boolean res = mapper.saveResource(resource);
        logger.info("Test: Save a Resource, Result: {}, resId={}", res, resource.getResId());
    }

    @Test
    public void testUpdateResource() {
        Random random = new Random(System.currentTimeMillis());
        Resource resource = mapper.findResourceByPrimaryKey(3L);
        if (resource == null) {
            logger.info("Test: Query Result is null");
            return;
        }
        resource.setResUrl("/api/v" + random.nextInt(10) +"/repertory");
        boolean res = mapper.updateResource(resource);
        logger.info("Test: Update a Resource, Result: {}", res);
    }

    @Test
    public void testFindResourceByPrimaryKey() {
        Resource resource = mapper.findResourceByPrimaryKey(3L);
        logger.info("Test: Query a Resource, Result: {}", resource);
    }

    @Test
    public void testDeleteResource() {
        Resource resource = mapper.findResourceByPrimaryKey(3L);
        if (resource == null) {
            logger.info("Test: Query Result is null");
            return;
        }
        boolean res = mapper.deleteResource(resource.getResId());
        logger.info("Test: Delete a Resource, Result: {}", res);
    }

}
