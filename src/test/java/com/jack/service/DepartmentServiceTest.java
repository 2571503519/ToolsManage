package com.jack.service;

import com.github.pagehelper.PageInfo;
import com.jack.config.RootConfig;
import com.jack.dto.DepartmentDTO;
import com.jack.pojo.entity.Department;
import com.jack.util.PageQuery;
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
public class DepartmentServiceTest {

    private final Logger logger = LoggerFactory.getLogger(DepartmentServiceTest.class);

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void testAddDepartment(){
        Department department = new Department();
        department.setDeptName("test3");
        department.setDeptPid(0L);
        department.setDeptManager("zhangsan");
        department.setDeptManagerPhone("123456");
        boolean res = departmentService.addDepartment(department);
        logger.info("Test : Add a Department Result {} {}",res, department);
    }
    @Test
    public void testFindDepartmentByName(){
        String deptName = "test3";
        Department department = departmentService.findDepartmentByName(deptName);
        logger.info("Test : Query a Department Result {}",department);
    }
    @Test
    public void testDeleteDepartment(){
        boolean res = departmentService.deleteDepartment(6L);
        logger.info("Test : Delete a Department Result {}",res);
    }
    @Test
    public void testUpdateDepartment(){
        Department department = departmentService.findDepartmentById(5L);
        if(department == null){
            logger.info("Test : Query Result is null ");
        }
        department.setDeptPid(3L);
        boolean res = departmentService.updateDepartment(department);
        logger.info("Test : Update a Department Result {}",res);
    }
    @Test
    public void testFindDepartmentByCondition(){
        Department department = new Department();
        department.setDeptName("");
        PageQuery pageQuery = new PageQuery(1,10);
        PageInfo pageInfo = departmentService.findDepartmentByCondition(pageQuery, department);
        logger.info("Test : Query Departments Result {}",pageInfo.getList());
    }
    @Test
    public void testFindDepartmentForSelect(){
        List<DepartmentDTO> departmentDTOList = departmentService.findDepartmentForSelect();
        logger.info("Test : Query Department Result {}",departmentDTOList);
    }
}
