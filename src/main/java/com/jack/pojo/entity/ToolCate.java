package com.jack.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Jackaroo Zhang on 2018/10/13.
 */
@ToString
public class ToolCate {

    @Getter @Setter private Long cateId;

    @Getter @Setter private String cateName;

    @Getter @Setter private Long catePid;




}
