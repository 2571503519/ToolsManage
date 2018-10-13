package com.jack.util;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Jackaroo Zhang on 2018/10/12.
 */
public class PageQuery {

    // 页码，从1开始
    @Getter @Setter private Integer page;

    // 单页大小，默认10条数据
    @Getter @Setter private Integer pageSize = 10;

    // 查询参数
    @Getter @Setter private String queryParam;

}
