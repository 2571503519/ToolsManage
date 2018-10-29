package com.jack.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Jackaroo Zhang on 2018/10/25.
 */
@ToString
public class AdminRole {

    @Getter @Setter private Long id;

    @Getter @Setter private Long adminId;

    @Getter @Setter private Long roleId;

}
