package com.jack.dao;

import com.jack.pojo.entity.Repertory;
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
@ContextConfiguration({"classpath:spring-dao.xml"})
public class RepertoryMapperTest {

    private final Logger logger = LoggerFactory.getLogger(RepertoryMapperTest.class);

    @Autowired
    private RepertoryMapper repertoryMapper;

    @Test
    public void testSaveRepertory(){
        Repertory repertory = new Repertory();
        repertory.setRepName("test1");
        repertory.setRepManager("zhangsan");
        repertory.setPhone("123456789");
        repertory.setDeptId(2L);
        repertory.setRepLocation("nanchang");
        repertory.setState(State.CommonState.NORMAL);
        boolean res = repertoryMapper.saveRepertory(repertory);
        logger.info("Test : Save a Repertory，Result {},repId {}",res,repertory.getRepId());
    }
    @Test
    public void testFindRepertoryByPrimaryKey(){
        Repertory repertory = repertoryMapper.findRepertoryByPrimaryKey(3L);
        logger.info("Test : Query a Repertory，Result{}",repertory);
    }
    @Test
    public void testFindRepertoryByName(){
        Repertory repertory = repertoryMapper.findRepertoryByName("test");
        logger.info("Test : Query a Repertory，Result{}",repertory);
    }
    @Test
    public void testUpdateRepertory(){
        Repertory repertory = repertoryMapper.findRepertoryByPrimaryKey(3L);
        if(repertory == null){
            logger.info("Test : Query Result is null");
            return;
        }
        repertory.setRepManager("lisi");
        repertory.setRepLocation("qingshanhu");
        boolean res = repertoryMapper.updateRepertory(repertory);
        logger.info("Test : Update a Repertory，Result {}",res);
    }
    @Test
    public void testUpdateStateByPrimaryKey(){
        Repertory repertory = repertoryMapper.findRepertoryByPrimaryKey(3L);
        if(repertory == null){
            logger.info("Test : Query Result is null");
            return ;
        }
        boolean res = repertoryMapper.updateStateByPrimaryKey(repertory.getRepId(), State.CommonState.DELETE);
        logger.info("Test Delete a Repertory Result {}",res);
    }
    @Test
    public void testFindAllByState(){
        List<Repertory> repertoryList = repertoryMapper.findAllByState(2);
        logger.info("Test Query Repertory Result {} ：{}", repertoryList.size() , repertoryList);
    }
}
