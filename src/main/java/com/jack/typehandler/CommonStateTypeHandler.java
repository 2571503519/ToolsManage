package com.jack.typehandler;

import com.jack.util.State;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jackaroo Zhang on 2018/10/13.
 * 管理员的状态枚举与状态码值的相互转换
 */
public class CommonStateTypeHandler implements TypeHandler<State.CommonState> {
    @Override
    public void setParameter(PreparedStatement ps, int i, State.CommonState parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getStateCode());
    }

    @Override
    public State.CommonState getResult(ResultSet rs, String columnName) throws SQLException {
        return State.CommonState.codeOf(rs.getInt(columnName));
    }

    @Override
    public State.CommonState getResult(ResultSet rs, int columnIndex) throws SQLException {
        return State.CommonState.codeOf(rs.getInt(columnIndex));
    }

    @Override
    public State.CommonState getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return State.CommonState.codeOf(cs.getInt(columnIndex));
    }
}
