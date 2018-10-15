package com.jack.pojo.entity;

import com.jack.util.State;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/11.
 */
@ToString
public class Admin {

    @Getter @Setter private Long adminId;

    @Getter @Setter private String username;

    @Getter @Setter private String password;

    @Getter @Setter private String idCard;

    @Getter @Setter private String adminName;

    @Getter @Setter private Long deptId;

    @Getter @Setter private List<Long> resIds;

    @Getter @Setter private State.AdminState state;

    @Getter @Setter private Timestamp gmtCreate;

    @Getter @Setter private Timestamp gmtModified;
}
