package com.jack.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.jack.dao.DepartmentMapper;
import com.jack.dto.DepartmentDTO;
import com.jack.exception.NameExistException;
import com.jack.pojo.entity.Department;
import com.jack.service.DepartmentService;
import com.jack.util.PageQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public boolean addDepartment(Department department) {
        Department existedDepartment = departmentMapper.findDepartmentByName(department.getDeptName());
        if(existedDepartment != null){
            throw new NameExistException("部门名称已存在");
        }
        return departmentMapper.saveDepartment(department);
    }

    @Override
    public Department findDepartmentByName(String deptName) {
        if(StringUtils.isBlank(deptName)){
            return null;
        }
        return departmentMapper.findDepartmentByName(deptName);
    }

    @Override
    public boolean deleteDepartment(Long deptId) {
        Integer count = departmentMapper.findCountByDepartmentPid(deptId);
        if(count.intValue() > 0){
            return false;
        }
        return departmentMapper.deleteDepartment(deptId);
    }

    @Override
    public boolean updateDepartment(Department department) {
        Department oldDepartment = departmentMapper.findDepartmentByPrimaryKey(department.getDeptId());
        if(!StringUtils.equalsIgnoreCase(department.getDeptName(), oldDepartment.getDeptName())){
            Department existedDepartment = departmentMapper.findDepartmentByName(department.getDeptName());
            if(existedDepartment != null){
                throw new NameExistException("部门名称已存在");
            }
        }
        return departmentMapper.updateDepartment(department);
    }

    @Override
    public Department findDepartmentById(Long deptId) {
        return departmentMapper.findDepartmentByPrimaryKey(deptId);
    }

    @Override
    public List<Department> findDepartmentByPid(Long deptPid) {
        return departmentMapper.findDepartmentByPid(deptPid);
    }

    @Override
    public PageInfo<DepartmentDTO> findAllDepartment(PageQuery pageQuery) {
        if(pageQuery == null) return null;
        PageHelper.startPage(pageQuery.getPage(), pageQuery.getPageSize());
        List<Department> departmentList = departmentMapper.findDepartmentByCondition(null);
        List<DepartmentDTO> departmentDTOList = Lists.newArrayList();
        for(Department department : departmentList){
            DepartmentDTO departmentDTO = this.transferToDepartmentDTO(department);
            departmentDTOList.add(departmentDTO);
        }
        return new PageInfo<>(departmentDTOList);
    }

    @Override
    public PageInfo<DepartmentDTO> findDepartmentByCondition(PageQuery pageQuery, Department department) {
        if(department != null){
            String deptName = new StringBuilder().append("%")
                    .append(StringUtils.trimToEmpty(department.getDeptName())).append("%").toString();
            String deptManager = new StringBuilder().append("%")
                    .append(StringUtils.trimToEmpty(department.getDeptManager())).append("%").toString();
            department.setDeptName(deptName);
            department.setDeptManager(deptManager);
        }
        PageHelper.startPage(pageQuery.getPage(), pageQuery.getPageSize());
        List<Department> departmentList = departmentMapper.findDepartmentByCondition(department);
        List<DepartmentDTO> departmentDTOList = Lists.newArrayList();
        for(Department tempDepartment : departmentList){
            DepartmentDTO departmentDTO = this.transferToDepartmentDTO(tempDepartment);
            departmentDTOList.add(departmentDTO);
        }
        return new PageInfo<>(departmentDTOList);
    }

    @Override
    public List<DepartmentDTO> findDepartmentForSelect() {
        List<DepartmentDTO> departmentDTOList = this.assembleDepartmentDTO(0L);
        return departmentDTOList;
    }



/*----------------------------私有方法---------------------------------------------------*/

    /**
     * 将department转换为departmentDTO
     * @param department
     * @return
     */
    private DepartmentDTO transferToDepartmentDTO(Department department){
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDeptId(department.getDeptId());
        departmentDTO.setDeptName(department.getDeptName());
        departmentDTO.setDeptManager(department.getDeptManager());
        departmentDTO.setDeptManagerPhone(department.getDeptManagerPhone());
        Department parentDepartment = departmentMapper.findDepartmentByPrimaryKey(department.getDeptPid());
        if(parentDepartment != null){
            departmentDTO.setParentDepartment(parentDepartment);
        }
        return departmentDTO;
    }

    /**
     * 根据父节点递归查询子节点
     * @param deptPid
     * @return
     */
    private List< DepartmentDTO> assembleDepartmentDTO(Long deptPid){
        List< DepartmentDTO> departmentDTOList = this.getChildList(deptPid);
        for(DepartmentDTO  departmentDTO : departmentDTOList){
            List<DepartmentDTO> temp = this.getChildList(departmentDTO.getDeptId());
            if (temp.size() > 0){
                departmentDTO.setChildDepartmentDTOList(this.assembleDepartmentDTO(departmentDTO.getDeptId()));
            }
        }
        return departmentDTOList;
    }

    /**
     * 查询以当前id为父id的部门集合
     * @param deptPid
     * @return
     */
    private List<DepartmentDTO> getChildList(Long deptPid){
        List<DepartmentDTO> departmentDTOList = Lists.newArrayList();
        List<Department> departmentList = departmentMapper.findDepartmentByPid(deptPid);
        if(departmentList.size() > 0) {
            for (Department department : departmentList) {
                DepartmentDTO departmentDTO = this.transferToDepartmentDTO(department);
                departmentDTOList.add(departmentDTO);
            }
        }
        return departmentDTOList;
    }
}
