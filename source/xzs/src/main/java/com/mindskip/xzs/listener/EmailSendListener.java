package com.mindskip.xzs.listener;

import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.event.OnRegistrationCompleteEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;



//消息目标
@Component
public class EmailSendListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Override
    //非空注解
    @NonNull
    //事件触发，打印消息发送者名
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        System.out.println("User register Email sender :" + user.getUserName());
    }
}
