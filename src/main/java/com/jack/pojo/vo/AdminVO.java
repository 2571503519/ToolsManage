package com.jack.pojo.vo;

import com.jack.util.State;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/30.
 */
@ToString
public class AdminVO {

    @Getter @Setter private Long adminId;

    @Getter @Setter private String username;

    @Getter @Setter private String password;

    @Getter @Setter private String idCard;

    @Getter @Setter private String adminName;

    @Getter @Setter private String deptName;

    @Getter @Setter private List<String> roleList;

    @Getter @Setter private List<Long> resIds;

    @Getter @Setter private State.AdminState state;

    @Getter @Setter private Timestamp gmtCreate;

    @Getter @Setter private Timestamp gmtModified;

}
