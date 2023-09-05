package com.mindskip.xzs.listener;

import com.mindskip.xzs.event.UserEvent;
import com.mindskip.xzs.service.UserEventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


//用户日志监听器
@Component
public class UserLogListener implements ApplicationListener<UserEvent> {

    private final UserEventLogService userEventLogService;

    /**
     * Instantiates a new User log listener.
     *
     * @param userEventLogService the user event log service
     */
    @Autowired
    public UserLogListener(UserEventLogService userEventLogService) {
        this.userEventLogService = userEventLogService;
    }

    //用户日志数据入库
    @Override
    public void onApplicationEvent(UserEvent userEvent) {
        userEventLogService.insertByFilter(userEvent.getUserEventLog());
    }

}
