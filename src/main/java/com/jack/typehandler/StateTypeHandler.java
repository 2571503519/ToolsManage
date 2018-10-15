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
public class StateTypeHandler implements TypeHandler<State.AdminState> {
    @Override
    public void setParameter(PreparedStatement ps, int i, State.AdminState parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getStateCode());
    }

    @Override
    public State.AdminState getResult(ResultSet rs, String columnName) throws SQLException {
        return State.AdminState.codeOf(rs.getInt(columnName));
    }

    @Override
    public State.AdminState getResult(ResultSet rs, int columnIndex) throws SQLException {
        return State.AdminState.codeOf(rs.getInt(columnIndex));
    }

    @Override
    public State.AdminState getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return State.AdminState.codeOf(cs.getInt(columnIndex));
    }
}
