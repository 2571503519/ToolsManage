package com.jack.service;


import com.github.pagehelper.PageInfo;
import com.jack.config.RootConfig;
import com.jack.pojo.entity.Repertory;
import com.jack.util.PageQuery;
import com.jack.util.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class RepertoryServiceTest {

    private final Logger logger = LoggerFactory.getLogger(RepertoryServiceTest.class);

    @Autowired
    private RepertoryService repertoryService;

    @Test
    public void testAddRepertory(){
        Repertory repertory = new Repertory();
        repertory.setRepName("test2");
        repertory.setRepManager("张三");
        repertory.setDeptId(1L);
        repertory.setRepLocation("南昌");
        repertory.setPhone("123456");
        boolean res = repertoryService.addRepertory(repertory);
        logger.info("Test : Save a Repertory Result {}{}", res, repertory);
    }
    @Test
    public void testDeleteRepertory(){
        boolean res = repertoryService.deleteRepertory(8L);
        logger.info("Test : Delete a Repertory Result {}",res);
    }
    @Test
    public void testFindOne(){
        Repertory repertory = repertoryService.findOne(4L);
        logger.info("Test : Query a Repertory Result {}", repertory);
    }
    @Test
    public void testUpdateRepertory(){
        Repertory repertory = repertoryService.findOne(4L);
        if(repertory == null){
            logger.info("Test : Query Result is null");
        }
        repertory.setPhone("654321");
        repertory.setRepManager("lisi");
        boolean res = repertoryService.updateRepertory(repertory);
        logger.info("Test Update a Repertory Result {}", res);
    }
    @Test
    public void testFindAllByState(){
        PageQuery pageQuery = new PageQuery(1,10);
        PageInfo pageInfo = repertoryService.findAllByState(pageQuery, State.CommonState.FORBID);
        logger.info("Test : Query Repertory Result {} {}", pageInfo.getTotal(), pageInfo.getList());
    }
    @Test
    public void testFindAllForSelect(){
        List<Repertory> repertorieList = repertoryService.findAllForSelect();
        logger.info("Test : Query Repertory Result {} ", repertorieList);
    }
}
