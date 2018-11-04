package com.jack.dao;

import com.jack.pojo.entity.Repertory;
import com.jack.util.State;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepertoryMapper {

    boolean saveRepertory(Repertory repertory);

    boolean updateRepertory(Repertory repertory);

    Repertory findRepertoryByPrimaryKey(Long repId);

    Repertory findRepertoryByName(String repName);

    boolean updateStateByPrimaryKey(@Param("repId") Long repId,@Param("state") State.CommonState state);

    List<Repertory> findAllByState(int stateCode);

    List<Repertory> findByCondition(Repertory repertory);
}
