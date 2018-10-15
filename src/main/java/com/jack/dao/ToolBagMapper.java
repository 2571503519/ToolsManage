package com.jack.dao;

import com.jack.pojo.entity.ToolBag;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolBagMapper {
    boolean saveToolBag(ToolBag toolBag);

    boolean updateToolBag(ToolBag toolBag);

    boolean deleteToolBag(Long tbId);

    ToolBag findToolBagByPrimaryKey(Long tbId);
}
