package com.jack.dao;

import com.jack.pojo.entity.ToolBag;
import com.jack.util.State;
import com.jack.util.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class ToolBagMapperTest {
    private final Logger logger = LoggerFactory.getLogger(ToolBagMapperTest.class);

    @Autowired
    private ToolBagMapper toolBagMapper;

    @Test
    public void testSaveToolBag(){
        ToolBag toolBag = new ToolBag();
        toolBag.setRfidCode("abcd");
        toolBag.setRfidReaderCode("ade");
        toolBag.setType(Type.ToolType.MEDIUM);
        toolBag.setRepId(1L);
        toolBag.setState(State.ToolState.READY);
        boolean res = toolBagMapper.saveToolBag(toolBag);
        logger.info("Test: Save a ToolBag Result:{} tbId : {}",res,toolBag.getTbId());
    }
    @Test
    public void testUpdateToolBag(){
        ToolBag toolBag = toolBagMapper.findToolBagByPrimaryKey(1L);
        if(toolBag == null){
            logger.info("Test : Query Result is null");
            return;
        }
        logger.info(toolBag.toString());
        toolBag.setRfidReaderCode("test update");
        toolBag.setType(Type.ToolType.BIG);
        boolean res = toolBagMapper.updateToolBag(toolBag);
        logger.info("Test : Update a ToolBag, Result {}",res);
    }
    @Test
    public void testDeleteToolBag(){
        ToolBag toolBag = toolBagMapper.findToolBagByPrimaryKey(1L);
        if(toolBag == null){
            logger.info("Test : Query Result is null");
            return;
        }
        logger.info(toolBag.toString());
        boolean res = toolBagMapper.deleteToolBag(toolBag.getTbId());
        logger.info("Test : Delete a ToolBag, Result {}",res);
    }
    @Test
    public void testFindToolBagByPrimaryKey(){
        ToolBag toolBag = toolBagMapper.findToolBagByPrimaryKey(1L);
        logger.info("Test : Query a ToolBag, Result {}",toolBag);
    }
}
