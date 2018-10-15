package com.jack.typehandler;

import com.jack.util.State;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jackaroo Zhang on 2018/10/15.
 */
public class ToolStateTypeHandler implements TypeHandler<State.ToolState> {
    @Override
    public void setParameter(PreparedStatement ps, int i, State.ToolState parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getStateCode());
    }

    @Override
    public State.ToolState getResult(ResultSet rs, String columnName) throws SQLException {
        return State.ToolState.codeOf(rs.getInt(columnName));
    }

    @Override
    public State.ToolState getResult(ResultSet rs, int columnIndex) throws SQLException {
        return State.ToolState.codeOf(rs.getInt(columnIndex));
    }

    @Override
    public State.ToolState getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return State.ToolState.codeOf(cs.getInt(columnIndex));
    }
}
