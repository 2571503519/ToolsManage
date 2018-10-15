package com.jack.pojo.entity;

import com.jack.util.State;
import com.jack.util.Type;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * Created by Jackaroo Zhang on 2018/10/13.
 */
@ToString
public class Tool {

    @Getter @Setter private Long toolId;

    @Getter @Setter private String toolName;

    @Getter @Setter private Long cateId;

    @Getter @Setter private Long repId;

    @Getter @Setter private String imgUrl;

    @Getter @Setter private Type.ToolType type;

    @Getter @Setter private State.ToolState state;

    @Getter @Setter private String rfidCode;

    @Getter @Setter private Timestamp gmtCreate;

    @Getter @Setter private Timestamp gmtModified;








}
