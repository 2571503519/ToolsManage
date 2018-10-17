package com.jack.dao.repertory;

import com.jack.pojo.entity.Repertory;
import org.springframework.stereotype.Repository;

@Repository
public interface RepertoryMapper {

    boolean saveRepertory(Repertory repertory);

    boolean updateRepertory(Repertory repertory);

    boolean deleteRepertory(Long repId);

    Repertory findRepertoryByPrimaryKey(Long repId);
}
