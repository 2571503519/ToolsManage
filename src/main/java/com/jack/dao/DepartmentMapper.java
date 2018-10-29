package com.jack.dao;


import com.jack.pojo.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {

    boolean saveDepartment(Department department);

    boolean updateDepartment(Department department);

    boolean deleteDepartment(Long deptId);

    Department findDepartmentByPrimaryKey(Long deptId);

    Department findDepartmentByName(String deptName);

    List<Department> findDepartmentByPid(Long deptPid);

    int findCountByDepartmentPid(Long deptPid);

    List<Department> findDepartmentByCondition(Department department);
}
