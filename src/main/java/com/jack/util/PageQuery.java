package com.jack.util;

import com.jack.exception.PageQueryException;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Jackaroo Zhang on 2018/10/12.
 */
public class PageQuery {

    public static final Integer DEFAULT_PAGE = 1;

    public static final Integer DEFAULT_PAGE_SIZE = 10;

    // 页码，默认从 DEFAULT_PAGE 开始
    @Getter @Setter private Integer page = DEFAULT_PAGE;

    // 单页大小，默认 DEFAULT_PAGE_SIZE 条数据
    @Getter @Setter private Integer pageSize = DEFAULT_PAGE_SIZE;

    // 查询参数
    @Getter @Setter private String queryParam;

    public PageQuery(int currentPage, int pageSize) {
        this.page = currentPage;
        this.pageSize = pageSize;
    }

    public static PageQuery build(int currentPage, int pageSize) throws PageQueryException {
        if (currentPage < 0 || pageSize < 0)
            throw new PageQueryException(currentPage, pageSize);
        return new PageQuery(currentPage, pageSize);
    }

    public static PageQuery build(int currentPage) throws PageQueryException  {
        return build(currentPage, DEFAULT_PAGE_SIZE);
    }

}
