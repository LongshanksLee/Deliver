package com.vinci.bean;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Author:Vinci_Ma
 * @Oescription: 用户信息
 * @Date Created in 2020-08-24-0:34
 * @Modified By:
 */
public class Users {
    private int id;
    private String uname;
    private String uphone;
    private String uidcard;
    private String ucode;
    private Timestamp signuptime;
    private Timestamp logintime;
    private int status;//用户的状态，0表示用户存在，1表示用户已被删除
    private boolean user;//是否是用户

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public Users(String uname, String uphone, String uidcard, String ucode) {
        this.uname = uname;
        this.uphone = uphone;
        this.uidcard = uidcard;
        this.ucode = ucode;
    }

    public Users() {
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", uphone='" + uphone + '\'' +
                ", uidcard='" + uidcard + '\'' +
                ", ucode='" + ucode + '\'' +
                ", signuptime=" + signuptime +
                ", logintime=" + logintime +
                ", stauts=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id &&
                status == users.status &&
                Objects.equals(uname, users.uname) &&
                Objects.equals(uphone, users.uphone) &&
                Objects.equals(uidcard, users.uidcard) &&
                Objects.equals(ucode, users.ucode) &&
                Objects.equals(signuptime, users.signuptime) &&
                Objects.equals(logintime, users.logintime);
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

    public Timestamp getSignuptime() {
        return signuptime;
    }

    public void setSignuptime(Timestamp signuptime) {
        this.signuptime = signuptime;
    }

    public Timestamp getLogintime() {
        return logintime;
    }

    public void setLogintime(Timestamp logintime) {
        this.logintime = logintime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Users(int id, String uname, String uphone, String uidcard, String ucode, Timestamp signuptime, Timestamp logintime, int status) {
        this.id = id;
        this.uname = uname;
        this.uphone = uphone;
        this.uidcard = uidcard;
        this.ucode = ucode;
        this.signuptime = signuptime;
        this.logintime = logintime;
        this.status = status;
    }
}
