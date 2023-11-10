package com.mind.zxks.repository;

import com.mind.zxks.domain.Message;
import com.mind.zxks.viewmodel.admin.message.MessagePageRequestVM;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//消息更新映射
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    //页面
    List<Message> page(MessagePageRequestVM requestVM);

    //根据id查询
    List<Message> selectByIds(List<Integer> ids);

    //添加已阅读
    int readAdd(Integer id);
}
