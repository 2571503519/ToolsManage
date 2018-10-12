package com.jack.dao;

import com.jack.pojo.entity.Resource;
import org.springframework.stereotype.Repository;

/**
 * Created by Jackaroo Zhang on 2018/10/12.
 */
@Repository
public interface ResourceMapper {

    boolean saveResource(Resource resource);

}
