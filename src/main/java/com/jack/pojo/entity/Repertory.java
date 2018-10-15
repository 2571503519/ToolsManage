package com.jack.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Repertory {

    @Getter @Setter private Long repId;

    @Getter @Setter private String repManager; //库房负责人

    @Getter @Setter private String phone;   //库房联系电话

    @Getter @Setter private Long deptId;   //库房所属部门

    @Getter @Setter private String repLocation;  //库房地址

    @Getter @Setter private Integer state;
}
