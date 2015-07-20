package dao.userDao;

import model.FindPasswordModel.Findpassword;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by youjm on 2015/7/19.
 */
public class FindpasswordMapper implements org.springframework.jdbc.core.RowMapper<Findpassword> {
    public Findpassword mapRow(ResultSet rs, int rowNum) throws  SQLException{
        Findpassword findpassword  =  new Findpassword();
        findpassword.setId(rs.getInt("id"));
        findpassword.setEmail(rs.getString("email"));
        findpassword.setOutdate(rs.getTimestamp("outdate"));
        findpassword.setValiday(rs.getString("validkey"));
        return findpassword;
    }
}
