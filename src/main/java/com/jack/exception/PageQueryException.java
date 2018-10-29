package com.jack.exception;

/**
 * Created by Jackaroo Zhang on 2018/10/25.
 */
public class PageQueryException extends IllegalArgumentException {


    public PageQueryException(String message) {
        super(message);
    }

    public PageQueryException(int currentPage, int pageSize) {
        this("PageQuery Param error: pageQuery is null or currentPage=" + currentPage + ", pageSize=" + pageSize);
    }
}
