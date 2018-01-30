package com.cdkj.coin.wallet.dto.res;

public class XN805041Res {
    private String userId;

    private String userReferee;

    public XN805041Res() {
    }

    public XN805041Res(String userId, String userReferee, Long amount) {
        this.userId = userId;
        this.userReferee = userReferee;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

}
