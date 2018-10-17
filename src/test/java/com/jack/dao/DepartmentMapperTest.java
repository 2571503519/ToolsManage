package com.jack.dao;


import com.jack.dao.dept.DepartmentMapper;
import com.jack.pojo.entity.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class DepartmentMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentMapperTest.class);

    @Autowired
    private DepartmentMapper departmentMapper;

    @Test
    public void testSaveDepartment(){
        Department department = new Department();
        department.setDeptName("test");
        department.setDeptPid(0L);
        department.setDeptManager("lisi");
        department.setDeptManagerPhone("123456789");
        boolean res = departmentMapper.saveDepartment(department);
        logger.info("Test : Save a Department, result {}, deptId {}",res, department.getDeptId());
    }
    @Test
    public void testFindDepartmentByPrimaryKey(){
        Department department = departmentMapper.findDepartmentByPrimaryKey(1L);
        logger.info("Test : Query a Department, Result {}",department);
    }
    @Test
    public void testUpdateDepartment(){
        Department department = departmentMapper.findDepartmentByPrimaryKey(1L);
        if(department == null){
            logger.info("Test : Query Result is null ");
            return;
        }
        department.setDeptManager("wangwu");
        department.setDeptManagerPhone("123654987");
        boolean res = departmentMapper.updateDepartment(department);
        logger.info("Test ：Update a Department, Result {}",res);
    }
    @Test
    public void testDeleteDepartment(){
        Department department = departmentMapper.findDepartmentByPrimaryKey(1L);
        if(department == null){
            logger.info("Test : Query Result is null ");
            return;
        }
        boolean res = departmentMapper.deleteDepartment(department.getDeptId());
        logger.info("Test ：Delete a Department, Result {}",res);
    }
}
