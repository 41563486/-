package com.mindskip.xzs.viewmodel.admin.user;

import com.mindskip.xzs.base.BasePage;




//用户事件分页请求vm
public class UserEventPageRequestVM extends BasePage {

    private Integer userId;

    private String userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
