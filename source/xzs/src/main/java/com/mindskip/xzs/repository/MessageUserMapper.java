package com.mindskip.xzs.repository;

import com.mindskip.xzs.domain.MessageUser;
import com.mindskip.xzs.viewmodel.second.user.MessageRequestVM;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//用户消息映射
@Mapper
public interface MessageUserMapper extends BaseMapper<MessageUser> {

    //根据消息id查询
    List<MessageUser> selectByMessageIds(List<Integer> ids);

    //数据插入
    int inserts(List<MessageUser> list);

    //学生页面
    List<MessageUser> studentPage(MessageRequestVM requestVM);

    //未阅读的数量
    Integer unReadCount(Integer userId);
}
