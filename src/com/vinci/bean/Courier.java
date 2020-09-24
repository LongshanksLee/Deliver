package com.vinci.bean;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Author:Vinci_Ma
 * @Oescription: 快递员信息
 * @Date Created in 2020-08-23-23:54
 * @Modified By:
 */
public class Courier {
    private int id;
    private String couriername;
    private String courierphone;
    private String idcard;
    private String code;
    private Timestamp signuptime;
    private Timestamp logintime;

    public Courier(String couriername, String courierphone, String idcard, String code) {
        this.couriername = couriername;
        this.courierphone = courierphone;
        this.idcard = idcard;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", couriername='" + couriername + '\'' +
                ", courierphone='" + courierphone + '\'' +
                ", idcard='" + idcard + '\'' +
                ", code='" + code + '\'' +
                ", signuptime=" + signuptime +
                ", logintime=" + logintime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courier courier = (Courier) o;
        return id == courier.id &&
                Objects.equals(couriername, courier.couriername) &&
                Objects.equals(courierphone, courier.courierphone) &&
                Objects.equals(idcard, courier.idcard) &&
                Objects.equals(code, courier.code) &&
                Objects.equals(signuptime, courier.signuptime) &&
                Objects.equals(logintime, courier.logintime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, couriername, courierphone, idcard, code, signuptime, logintime);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCouriername() {
        return couriername;
    }

    public void setCouriername(String couriername) {
        this.couriername = couriername;
    }

    public String getCourierphone() {
        return courierphone;
    }

    public void setCourierphone(String courierphone) {
        this.courierphone = courierphone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Courier(int id, String couriername, String courierphone, String idcard, String code, Timestamp signuptime, Timestamp logintime) {
        this.id = id;
        this.couriername = couriername;
        this.courierphone = courierphone;
        this.idcard = idcard;
        this.code = code;
        this.signuptime = signuptime;
        this.logintime = logintime;
    }

    public Courier() {
    }
}
