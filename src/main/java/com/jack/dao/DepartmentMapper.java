package com.jack.dao;


import com.jack.pojo.entity.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentMapper {

    boolean saveDepartment(Department department);

    boolean updateDepartment(Department department);

    boolean deleteDepartment(Long deptId);

    Department findDepartmentByPrimaryKey(Long deptId);
}
