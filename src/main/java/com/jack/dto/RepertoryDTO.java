package com.jack.dto;

import com.jack.pojo.entity.Department;
import com.jack.util.State;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class RepertoryDTO {

    @Getter @Setter private Long repId;

    @Getter @Setter private String repName; //库房名称

    @Getter @Setter private String repManager; //库房负责人

    @Getter @Setter private String phone;   //库房联系电话

    @Getter @Setter private String repLocation;  //库房地址

    @Getter @Setter private State.CommonState state;  //状态

    @Getter @Setter private Department department; //所属部门
}
