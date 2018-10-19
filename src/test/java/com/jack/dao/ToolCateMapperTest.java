package com.jack.dao;

import com.jack.pojo.entity.ToolCate;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class ToolCateMapperTest {
    private final Logger logger = LoggerFactory.getLogger(ToolCateMapperTest.class);

    @Autowired
    private ToolCateMapper mapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private List<Long> allCateIds;

    @Test
    public void testGetAllId() throws SQLException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Connection conn = sqlSession.getConnection();
        PreparedStatement psmt = conn.prepareStatement("select cate_id from tm_tool_cate");
        ResultSet rs = psmt.executeQuery();
        allCateIds = new ArrayList<>(rs.getRow());
        while (rs.next()) {
            long cate_id = rs.getLong("cate_id");
            allCateIds.add(cate_id);
        }
    }

    @Test
    public void testSaveToolCate() {
        ToolCate cate = new ToolCate();
        cate.setCateName("便携类");
        cate.setCatePid(0L);
        boolean res = mapper.saveToolCate(cate);
        logger.info("Test: save a toolCate, Result: {}, cateId={}", res, cate.getCateId());
    }

    @Test
    public void testFindToolCateByPrimaryKey() {
        ToolCate cate = mapper.findToolCateByPrimaryKey(1L);
        logger.info("Test: query a toolCate, Result: {}", cate);
    }

    @Test
    public void testUpdateToolCate() {
        ToolCate cate = mapper.findToolCateByPrimaryKey(2L);
        if (cate == null) logger.info("Result is null");
        else {
            cate.setCateName("重型");
            boolean res = mapper.updateToolCate(cate);
            logger.info("Test: update a toolCate, Result: {}", res);
        }
    }

    @Test
    public void testDeleteToolCate() {
        boolean res = mapper.deleteToolCate(2L);
        logger.info("Test: delete a toolCate, Result: {}", res);
    }

}
