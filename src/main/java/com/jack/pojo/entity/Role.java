package com.jack.pojo.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Created by Jackaroo Zhang on 2018/10/20.
 */
public class Role {

    @Getter @Setter private Long roleId;

    @Getter @Setter private String roleName;

    @Getter @Setter private Timestamp gmtCreate;

    @Getter @Setter private Timestamp gmtModified;

}
