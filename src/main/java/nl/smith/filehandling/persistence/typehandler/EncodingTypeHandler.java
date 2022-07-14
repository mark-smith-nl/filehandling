package nl.smith.filehandling.persistence.typehandler;

import nl.smith.filehandling.domain.PersistentFile;
import nl.smith.filehandling.enums.Encoding;
import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author m.smithhva.nl
 */
public class EncodingTypeHandler extends EnumTypeHandler<Encoding> {

    public EncodingTypeHandler(Class<Encoding> type) {
        super(type);
    }

    public void setNonNullParameter(PreparedStatement ps, int i, Encoding parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setString(i, parameter.getType());
        } else {
            ps.setObject(i, parameter.getType(), jdbcType.TYPE_CODE);
        }

    }

    public Encoding getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String type = rs.getString(columnName);
        return type == null ? null : Encoding.getByType(type);
    }

    public Encoding getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String type = rs.getString(columnIndex);
        return type == null ? null : Encoding.getByType(type);
    }

    public Encoding getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String type = cs.getString(columnIndex);
        return type == null ? null : Encoding.getByType(type);
    }
}
