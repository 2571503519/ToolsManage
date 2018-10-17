package com.jack.dao.admin;

import com.jack.pojo.entity.Resource;
import org.springframework.stereotype.Repository;

/**
 * Created by Jackaroo Zhang on 2018/10/12.
 */
@Repository
public interface ResourceMapper {

    boolean saveResource(Resource resource);

    boolean updateResource(Resource resource);

    Resource findResourceByPrimaryKey(Long resId);

    boolean deleteResource(Long resId);

}
