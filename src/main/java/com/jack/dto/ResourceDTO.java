package com.jack.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/28.
 */
@ToString
public class ResourceDTO {

    @Getter @Setter private Long resId;

    @Getter @Setter private String resName;

    @Getter @Setter private String resPermission;

    @Getter @Setter private String resUrl;

    @Getter @Setter private Timestamp gmtCreate;

    @Getter @Setter private Timestamp gmtModified;

    @Getter @Setter private List<ResourceDTO> children;

}
