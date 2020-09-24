package com.vinci.bean;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Author:Vinci_Ma
 * @Oescription:
 * @Date Created in 2020-08-25-22:05
 * @Modified By:
 */
public class BootStrapTableCourier {
    private int id;
    private String couriername;
    private String courierphone;
    private String idcard;
    private String code;
    private String signuptime;
    private String logintime;

    @Override
    public String toString() {
        return "BootStrapTableCourier{" +
                "id=" + id +
                ", couriername='" + couriername + '\'' +
                ", courierphone='" + courierphone + '\'' +
                ", idcard='" + idcard + '\'' +
                ", code='" + code + '\'' +
                ", signuptime='" + signuptime + '\'' +
                ", logintime='" + logintime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BootStrapTableCourier that = (BootStrapTableCourier) o;
        return id == that.id &&
                Objects.equals(couriername, that.couriername) &&
                Objects.equals(courierphone, that.courierphone) &&
                Objects.equals(idcard, that.idcard) &&
                Objects.equals(code, that.code) &&
                Objects.equals(signuptime, that.signuptime) &&
                Objects.equals(logintime, that.logintime);
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

    public BootStrapTableCourier(int id, String couriername, String courierphone, String idcard, String code, String signuptime, String logintime) {
        this.id = id;
        this.couriername = couriername;
        this.courierphone = courierphone;
        this.idcard = idcard;
        this.code = code;
        this.signuptime = signuptime;
        this.logintime = logintime;
    }

    public BootStrapTableCourier() {
    }
}
