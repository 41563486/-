package com.mind.zxks.repository;

import com.mind.zxks.domain.UserEventLog;
import com.mind.zxks.domain.other.KeyValue;
import com.mind.zxks.viewmodel.admin.user.UserEventPageRequestVM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

//用户日志事件映射
@Mapper
public interface UserEventLogMapper extends BaseMapper<UserEventLog> {

    //通过用户id查询用户事件日志
    List<UserEventLog> getUserEventLogByUserId(Integer id);

    //页面
    List<UserEventLog> page(UserEventPageRequestVM requestVM);

    //通过日期查询数量
    List<KeyValue> selectCountByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
