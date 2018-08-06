package com.sample.login;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/6
 * @Description
 */
public class UserInfo {

    private String accountId;
    private String userName;

    public UserInfo(){

    }

    public UserInfo(String accountId,String userName){
        this.accountId = accountId;
        this.userName = userName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
