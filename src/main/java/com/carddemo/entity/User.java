package com.carddemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @NotNull
    @Size(max = 8)
    @Column(name = "sec_usr_id", length = 8)
    private String secUsrId;

    @NotNull
    @Size(max = 20)
    @Column(name = "sec_usr_fname", length = 20)
    private String secUsrFname;

    @NotNull
    @Size(max = 20)
    @Column(name = "sec_usr_lname", length = 20)
    private String secUsrLname;

    @NotNull
    @Size(max = 8)
    @Column(name = "sec_usr_pwd", length = 8)
    private String secUsrPwd;

    @NotNull
    @Size(max = 1)
    @Column(name = "sec_usr_type", length = 1)
    private String secUsrType;

    public User() {}

    public String getSecUsrId() {
        return secUsrId;
    }

    public void setSecUsrId(String secUsrId) {
        this.secUsrId = secUsrId;
    }

    public String getSecUsrFname() {
        return secUsrFname;
    }

    public void setSecUsrFname(String secUsrFname) {
        this.secUsrFname = secUsrFname;
    }

    public String getSecUsrLname() {
        return secUsrLname;
    }

    public void setSecUsrLname(String secUsrLname) {
        this.secUsrLname = secUsrLname;
    }

    public String getSecUsrPwd() {
        return secUsrPwd;
    }

    public void setSecUsrPwd(String secUsrPwd) {
        this.secUsrPwd = secUsrPwd;
    }

    public String getSecUsrType() {
        return secUsrType;
    }

    public void setSecUsrType(String secUsrType) {
        this.secUsrType = secUsrType;
    }

}
