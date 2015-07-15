package model.FindPasswordModel;

import java.sql.Timestamp;

/**
 * Created by youjm on 2015/7/14.
 */
public class Findpassword {
    private Integer id;
    private String email;
    private Timestamp outdate;
    private String  validkey;

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getOutdate() {
        return outdate;
    }

    public String getValidkey() {
        return validkey;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOutdate(Timestamp outdate) {
        this.outdate = outdate;
    }

    public void setValiday(String validkey) {
        this.validkey = validkey;
    }
}
