package com.jack.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.jack.dao.DepartmentMapper;
import com.jack.dao.RepertoryMapper;
import com.jack.dto.RepertoryDTO;
import com.jack.exception.NameExistException;
import com.jack.pojo.entity.Department;
import com.jack.pojo.entity.Repertory;
import com.jack.service.RepertoryService;
import com.jack.util.PageQuery;
import com.jack.util.State;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepertoryServiceImpl implements RepertoryService {

    @Autowired
    private RepertoryMapper repertoryMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public boolean addRepertory(Repertory repertory) {
        Repertory existedRepertory = repertoryMapper.findRepertoryByName(repertory.getRepName());
        if(existedRepertory != null){
            throw new NameExistException("库房名称已存在");
        }
        repertory.setState(State.CommonState.NORMAL);
        return repertoryMapper.saveRepertory(repertory);
    }

    @Override
    public boolean deleteRepertory(Long repId) {
        return repertoryMapper.updateStateByPrimaryKey(repId, State.CommonState.DELETE);
    }

    @Override
    public Repertory findOne(Long repId) {
        return repertoryMapper.findRepertoryByPrimaryKey(repId);
    }

    @Override
    public boolean updateRepertory(Repertory repertory) {
        Repertory oldRepertory = repertoryMapper.findRepertoryByPrimaryKey(repertory.getRepId());
        if(!StringUtils.equalsIgnoreCase(oldRepertory.getRepName(), repertory.getRepName())){
            Repertory existedRepertory = repertoryMapper.findRepertoryByName(repertory.getRepName());
            if(existedRepertory != null){
                throw new NameExistException("部门名称已存在");
            }
        }
        return repertoryMapper.updateRepertory(repertory);
    }

    @Override
    public PageInfo<RepertoryDTO> findAllByState(PageQuery pageQuery, State.CommonState state) {
        if(pageQuery == null) return null;
        PageHelper.startPage(pageQuery.getPage(), pageQuery.getPageSize());
        List<Repertory> repertoryList = repertoryMapper.findAllByState(state.getStateCode());
        List<RepertoryDTO> repertoryDTOList = Lists.newArrayList();
        for(Repertory repertory : repertoryList){
            RepertoryDTO repertoryDTO = this.transferToRepertoryDTO(repertory);
            repertoryDTOList.add(repertoryDTO);
        }
        return new PageInfo<>(repertoryDTOList);
    }

    @Override
    public PageInfo<RepertoryDTO> findByCondition(PageQuery pageQuery, Repertory repertory) {
        if(pageQuery == null) return null;
        PageHelper.startPage(pageQuery.getPage(), pageQuery.getPageSize());
        List<Repertory> repertoryList = repertoryMapper.findByCondition(repertory);
        List<RepertoryDTO> repertoryDTOList = Lists.newArrayList();
        for(Repertory temp : repertoryList){
            RepertoryDTO repertoryDTO = this.transferToRepertoryDTO(temp);
            repertoryDTOList.add(repertoryDTO);
        }
        return new PageInfo<>(repertoryDTOList);
    }

    @Override
    public List<Repertory> findAllForSelect() {
        return repertoryMapper.findAllByState(State.CommonState.NORMAL.getStateCode());
    }







/*----------------------------------私有方法------------------------------------------------*/

    /**
     * 将Repertory转化为RepertoryDTO
     * @param repertory
     * @return
     */
    private RepertoryDTO transferToRepertoryDTO(Repertory repertory){
        RepertoryDTO repertoryDTO = new RepertoryDTO();
        repertoryDTO.setRepId(repertory.getRepId());
        repertoryDTO.setRepName(repertory.getRepName());
        repertoryDTO.setRepManager(repertory.getRepManager());
        repertoryDTO.setPhone(repertory.getPhone());
        repertoryDTO.setRepLocation(repertory.getRepLocation());
        repertoryDTO.setState(repertory.getState());
        Department department = departmentMapper.findDepartmentByPrimaryKey(repertory.getDeptId());
        repertoryDTO.setDepartment(department);
        return repertoryDTO;
    }
}
