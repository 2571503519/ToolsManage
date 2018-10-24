package com.jack.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * Created by Jackaroo Zhang on 2018/10/24.
 */
@ToString
public class RoleResource {

    @Getter @Setter private Long roleId;

    @Getter @Setter private String roleName;

    @Getter @Setter private Long resId;

    @Getter @Setter private String resName;

    @Getter @Setter private String resPermission;

    @Getter @Setter private String resUrl;

    // role的创建时间
    @Getter @Setter private Timestamp gmtCreate;

    // role的修改时间
    @Getter @Setter private Timestamp gmtModified;

}
