package com.jack.dao;

import com.jack.pojo.entity.Tool;
import com.jack.util.State;
import com.jack.util.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Jackaroo Zhang on 2018/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class ToolMapperTest {

    private final Logger logger = LoggerFactory.getLogger(ToolMapperTest.class);

    @Autowired
    private ToolMapper mapper;

    @Test
    public void testSaveTool() {
        Tool tool = new Tool();
        tool.setToolName("锤子");
        tool.setCateId(2L);
        tool.setImgUrl("http://www.baidu.com/pic/1.png");
        tool.setRepId(2L);
        tool.setRfidCode("ssfsdfsd");
        tool.setType(Type.ToolType.SMALL);
        tool.setState(State.ToolState.READY);
        boolean res = mapper.saveTool(tool);
        logger.info("Test: Save a Tool, Result: {}, toolId={}", res, tool.getToolId());
    }

    @Test
    public void testFindToolByPrimaryKey() {
        Tool tool = mapper.findToolByPrimaryKey(1L);
        if (tool == null) {
            logger.info("Result is null");
        } else {
            logger.info("Test: query a Tool, Result: {}", tool);
        }
    }

    @Test
    public void testUpdateTool() {
        Tool tool = mapper.findToolByPrimaryKey(1L);
        if (tool == null) {
            logger.info("Result is null");
        } else {
            tool.setState(State.ToolState.REPAIR);
            tool.setType(Type.ToolType.MEDIUM);
            boolean res = mapper.updateTool(tool);
            logger.info("Test: Update a Tool, Result: {}", res);
        }
    }

    @Test
    public void testDeleteTool() {
        boolean res = mapper.deleteTool(1L);
        logger.info("Test: delete a tool, Result: {}", res);
    }

}
