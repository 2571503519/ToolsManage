package com.jack.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
public class ToolBag {

    @Getter @Setter private Long tbId;

    @Getter @Setter private String rfidCode; //工具包上的RFID标签码

    @Getter @Setter private String rfidReaderCode; //RFID读取器机器码

    @Getter @Setter private Integer type; //工具包类型 小 1、中 2、大 3

    @Getter @Setter private Long repId; //所属库房Id

    @Getter @Setter private Integer state; //工具包状态 正常 2、禁用 1、删除 0

    @Getter @Setter private Timestamp gmtCreate;

    @Getter @Setter private Timestamp gmtModified;
}
