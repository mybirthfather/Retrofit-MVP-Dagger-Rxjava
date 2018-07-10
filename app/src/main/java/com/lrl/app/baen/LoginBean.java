package com.lrl.app.baen;

/**
 * Created by ZHP on 2017/7/19.
 */

public class LoginBean extends BaseEntity {


    /**
     * id : 1
     * nickname : 呆萌的吕奇
     * amount : 1000000
     * freeze_amount : 0
     * points : 0
     * freeze_points : 0
     * login_token : 06c23e1b8500a979b63541a83868a7d4
     */

    private String id;
    private String nickname;
    private String username;
    private String amount;
    private String freezeAmount;
    private String points;
    private String loginToken;
    private String rebate;
    private String phone;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(String freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getRebate() {
        return rebate;
    }

    public void setRebate(String rebate) {
        this.rebate = rebate;
    }
}
