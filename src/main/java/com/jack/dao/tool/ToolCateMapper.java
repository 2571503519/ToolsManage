package com.jack.dao.tool;

import com.jack.pojo.entity.ToolCate;
import org.springframework.stereotype.Repository;

/**
 * Created by Jackaroo Zhang on 2018/10/15.
 */
@Repository
public interface ToolCateMapper {

    boolean saveToolCate(ToolCate toolCate);

    boolean updateToolCate(ToolCate toolCate);

    boolean deleteToolCate(Long cateId);

    ToolCate findToolCateByPrimaryKey(Long cateId);

}
