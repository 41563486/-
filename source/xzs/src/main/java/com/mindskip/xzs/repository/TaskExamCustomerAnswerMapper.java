package com.mindskip.xzs.repository;

import com.mindskip.xzs.domain.TaskExamCustomerAnswer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//用户试卷题目答案映射
@Mapper
public interface TaskExamCustomerAnswerMapper extends BaseMapper<TaskExamCustomerAnswer> {

    //通过id获取
    TaskExamCustomerAnswer getByTUid(@Param("tid") Integer tid, @Param("uid") Integer uid);

    //通过id查询
    List<TaskExamCustomerAnswer> selectByTUid(@Param("taskIds") List<Integer> taskIds, @Param("uid") Integer uid);
}
