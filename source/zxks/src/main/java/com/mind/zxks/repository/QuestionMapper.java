package com.mind.zxks.repository;

import com.mind.zxks.domain.other.KeyValue;
import com.mind.zxks.domain.Question;
import com.mind.zxks.viewmodel.admin.question.QuestionPageRequestVM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;




//题目映射器
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

    //页面
    List<Question> page(QuestionPageRequestVM requestVM);

    //根据id查询
    List<Question> selectByIds(@Param("ids") List<Integer> ids);

    //查询所有数量
    Integer selectAllCount();

    //根据时间查询数量
    List<KeyValue> selectCountByDate(@Param("startTime") Date startTime,@Param("endTime") Date endTime);
}
