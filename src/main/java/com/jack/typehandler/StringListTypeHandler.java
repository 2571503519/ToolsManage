package com.jack.typehandler;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/13.
 * 完成String与List之间的相互转换，例如 "1,2,3,4" <=> List: [1, 2, 3, 4]
 */
public class StringListTypeHandler implements TypeHandler<List<Long>> {
    // 默认分隔符
    private static final String SEPARATOR = ",";

    @Override
    public void setParameter(PreparedStatement ps, int i, List<Long> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, StringUtils.join(parameter.toArray(), SEPARATOR));
    }

    @Override
    public List<Long> getResult(ResultSet rs, String columnName) throws SQLException {
        String resIds = rs.getString(columnName);
        return stringToList(resIds, SEPARATOR);
    }

    @Override
    public List<Long> getResult(ResultSet rs, int columnIndex) throws SQLException {
        String resIds = rs.getString(columnIndex);
        return stringToList(resIds, SEPARATOR);
    }

    @Override
    public List<Long> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String resIds = cs.getString(columnIndex);
        return stringToList(resIds, SEPARATOR);
    }

    private List<Long> stringToList(String str, String separator) {
        if (separator == null) separator = SEPARATOR;
        String[] resIdsArray = StringUtils.split(str, separator);
        List<Long> result = new ArrayList<>(resIdsArray.length);
        for (String resId : resIdsArray) {
            result.add(Long.parseLong(resId));
        }
        return result;
    }
}
