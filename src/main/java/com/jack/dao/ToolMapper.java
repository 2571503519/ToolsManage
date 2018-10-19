package com.jack.dao;

import com.jack.pojo.entity.Tool;
import org.springframework.stereotype.Repository;

/**
 * Created by Jackaroo Zhang on 2018/10/15.
 */
@Repository
public interface ToolMapper {

    boolean saveTool(Tool tool);

    Tool findToolByPrimaryKey(Long toolId);

    boolean updateTool(Tool tool);

    boolean deleteTool(Long toolId);

}
