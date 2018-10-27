package com.jack.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * Created by Jackaroo Zhang on 2018/10/20.
 */
@ToString
public class Role {

    @Getter @Setter private Long roleId;

    @Getter @Setter private String roleName;

    @Getter @Setter private String roleCode;

    @Getter @Setter private Timestamp gmtCreate;

    @Getter @Setter private Timestamp gmtModified;

}
