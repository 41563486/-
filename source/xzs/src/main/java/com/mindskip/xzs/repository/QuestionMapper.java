package com.mindskip.xzs.repository;

import com.mindskip.xzs.domain.other.KeyValue;
import com.mindskip.xzs.domain.Question;
import com.mindskip.xzs.viewmodel.admin.question.QuestionPageRequestVM;
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
