package com.jack.pojo.entity;

<<<<<<< HEAD
import com.jack.util.State;
=======
>>>>>>> f7e2d94cef630a1e63c0880d35e70215307cb7a9
import com.jack.util.Type;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
public class ToolBag {

    @Getter @Setter private Long tbId;

    @Getter @Setter private String rfidCode; //工具包上的RFID标签码

    @Getter @Setter private String rfidReaderCode; //RFID读取器机器码

<<<<<<< HEAD
    @Getter @Setter private Type.ToolType type; //工具包型号，大 BIG、中 MEDIUM、小 SMALL
=======
<<<<<<< HEAD
    @Getter @Setter private Type.ToolType type; //工具包类型 小 1、中 2、大 3
=======
    //TODO: 这里的type属性使用com.jack.util.Type.ToolType枚举类，对应jdbcType为VARCHAR
    @Getter @Setter private Integer type; //工具包类型 小 1、中 2、大 3
>>>>>>> 02f888b5563b7a1f6883ef3cca6b7886a1257c1b
>>>>>>> f7e2d94cef630a1e63c0880d35e70215307cb7a9

    @Getter @Setter private Long repId; //所属库房Id

    // 关于只包含几种值的类型，建议使用枚举，这样语义更加明确
    @Getter @Setter private State.ToolState state; //工具包当前状态，使用中 3,、待使用 2、维修中 1、报废 0

    @Getter @Setter private Timestamp gmtCreate;

    @Getter @Setter private Timestamp gmtModified;
}
