package com.jack.service;

import com.github.pagehelper.PageInfo;
import com.jack.dto.DepartmentDTO;
import com.jack.pojo.entity.Department;
import com.jack.util.PageQuery;

import java.util.List;

/**
 * 部门业务接口
 */
public interface DepartmentService {
    /**
     * 添加部门
     * @param department
     * @return
     */
    boolean addDepartment(Department department);

    /**
     * 删除指定部门
     * @param deptId 部门id
     * @return
     */
   boolean deleteDepartment(Long deptId);

    /**
     * 更新部门信息
     * @param department
     * @return
     */
   boolean updateDepartment(Department department);

    /**
     * 分页查询所有部门
     * @param pageQuery  分页参数 当前页数 ，页面记录数
     * @return
     */
   PageInfo<DepartmentDTO> findAllDepartment(PageQuery pageQuery);
    /**
     * 根据部门Id查询部门
     * @param deptId
     * @return
     */
   Department findDepartmentById(Long deptId);

    /**
     * 根据父id查询部门
     * @param deptPid
     * @return
     */
   List<Department> findDepartmentByPid(Long deptPid);

    /**
     * 根据部门名称查询部门
     * @param deptName
     * @return
     */
    Department findDepartmentByName(String deptName);

    /**
     * 条件查询部门
     * @param pageQuery 分页参数  当前页数，页面记录数
     * @param department  条件查询  部门名称 部门管理员
     * @return
     */
   PageInfo<DepartmentDTO> findDepartmentByCondition(PageQuery pageQuery, Department department);

    /**
     * 分级查询所有部门
     * @return
     */
   List<DepartmentDTO> findDepartmentForSelect();

}
