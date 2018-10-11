package com.jack.pojo.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Created by Jackaroo Zhang on 2018/10/11.
 */
public class Resource {

    @Getter @Setter private Long resId;

    @Getter @Setter private String resName;

    @Getter @Setter private String resUrl;

    @Getter @Setter private Long resPid;

    @Getter @Setter private Timestamp gmtCreate;

    @Getter @Setter private Timestamp gmtModified;

}
