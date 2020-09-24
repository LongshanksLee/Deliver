package com.vinci.bean;

import java.util.Objects;

/**
 * @Author:Vinci_Ma
 * @Oescription:
 * @Date Created in 2020-08-26-17:33
 * @Modified By:
 */
public class BootStrapTableUsers {
    private int id;
    private String uname;
    private String uphone;
    private String uidcard;
    private String ucode;
    private String signuptime;
    private String logintime;
    private int status;

    @Override
    public String toString() {
        return "BootStrapTableUsers{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", uphone='" + uphone + '\'' +
                ", uidcard='" + uidcard + '\'' +
                ", ucode='" + ucode + '\'' +
                ", signuptime='" + signuptime + '\'' +
                ", logintime='" + logintime + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BootStrapTableUsers that = (BootStrapTableUsers) o;
        return id == that.id &&
                status == that.status &&
                Objects.equals(uname, that.uname) &&
                Objects.equals(uphone, that.uphone) &&
                Objects.equals(uidcard, that.uidcard) &&
                Objects.equals(ucode, that.ucode) &&
                Objects.equals(signuptime, that.signuptime) &&
                Objects.equals(logintime, that.logintime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uname, uphone, uidcard, ucode, signuptime, logintime, status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUidcard() {
        return uidcard;
    }

    public void setUidcard(String uidcard) {
        this.uidcard = uidcard;
    }

    public String getUcode() {
        return ucode;
    }

    public void setUcode(String ucode) {
        this.ucode = ucode;
    }

    public String getSignuptime() {
        return signuptime;
    }

    public void setSignuptime(String signuptime) {
        this.signuptime = signuptime;
    }

    public String getLogintime() {
        return logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BootStrapTableUsers(int id, String uname, String uphone, String uidcard, String ucode, String signuptime, String logintime, int status) {
        this.id = id;
        this.uname = uname;
        this.uphone = uphone;
        this.uidcard = uidcard;
        this.ucode = ucode;
        this.signuptime = signuptime;
        this.logintime = logintime;
        this.status = status;
    }

    public BootStrapTableUsers() {
    }
}
