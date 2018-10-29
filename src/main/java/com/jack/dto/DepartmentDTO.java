package com.jack.dto;

import com.jack.pojo.entity.Department;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
public class DepartmentDTO {

    @Getter @Setter private Long deptId;

    @Getter @Setter private String deptName;   //部门名称

    @Getter @Setter private String deptManager;  //部门管理员

    @Getter @Setter private String deptManagerPhone;   //部门管理员联系方式

    @Getter @Setter private Department parentDepartment;  //上级部门对象

    @Getter @Setter private List<DepartmentDTO> childDepartmentDTOList;  //子部门集合

}
