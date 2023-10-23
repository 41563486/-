package com.mindskip.xzs.event;

import com.mindskip.xzs.domain.UserEventLog;
import org.springframework.context.ApplicationEvent;


//用户事件
public class UserEvent extends ApplicationEvent {

    private final UserEventLog userEventLog;

    /**
     * Instantiates a new User event.
     *
     * @param userEventLog the user event log
     */
    public UserEvent(final UserEventLog userEventLog) {
        super(userEventLog);
        this.userEventLog = userEventLog;
    }

    /**
     * Gets user event log.
     *
     * @return the user event log
     */
    public UserEventLog getUserEventLog() {
        return userEventLog;
    }
}
