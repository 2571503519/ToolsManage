package com.jack.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Department {

    @Getter @Setter private Long deptId;

    @Getter @Setter private String deptName;   //部门名称

    @Getter @Setter private Long deptPid;     //上级部门ID

    @Getter @Setter private String deptManager;  //部门管理员

    @Getter @Setter private String deptManagerPhone;   //部门管理员联系方式
}
