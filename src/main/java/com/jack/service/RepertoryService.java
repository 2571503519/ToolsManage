package com.jack.service;

import com.github.pagehelper.PageInfo;
import com.jack.dto.RepertoryDTO;
import com.jack.pojo.entity.Repertory;
import com.jack.util.PageQuery;
import com.jack.util.State;

import java.util.List;

public interface RepertoryService {

    /**
     * 添加库房
     * @param repertory
     * @return
     */
    boolean addRepertory(Repertory repertory);

    /**
     * 删除指定库房  将库房的状态更改为DELETE
     * @param repId
     * @return
     */
    boolean deleteRepertory(Long repId);

    /**
     * 通过库房id查询
     * @param repId
     * @return
     */
    Repertory findOne(Long repId);

    /**
     * 更新库房信息
     * @param repertory
     * @return
     */
    boolean updateRepertory(Repertory repertory);

    /**
     * 根据状态查询库房
     * @param pageQuery
     * @param state 状态值
     * @return
     */
    PageInfo<RepertoryDTO> findAllByState(PageQuery pageQuery, State.CommonState state);

    /**
     * 条件查询库房
     * @param pageQuery
     * @param repertory
     * @return
     */
    PageInfo<RepertoryDTO> findByCondition(PageQuery pageQuery, Repertory repertory);

    /**
     * 查询可用库房集合
     * @return
     */
    List<Repertory> findAllForSelect();
}
