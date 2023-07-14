package com.mindskip.xzs.viewmodel.admin.message;

import com.mindskip.xzs.base.BasePage;



//信息分页请求vm
public class MessagePageRequestVM extends BasePage {
    private String sendUserName;

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }
}
