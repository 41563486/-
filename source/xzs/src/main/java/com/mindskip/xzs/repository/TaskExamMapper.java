package com.mindskip.xzs.repository;

import com.mindskip.xzs.domain.TaskExam;
import com.mindskip.xzs.viewmodel.admin.task.TaskPageRequestVM;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//任务试卷映射器
@Mapper
public interface TaskExamMapper extends BaseMapper<TaskExam> {

    //页面
    List<TaskExam> page(TaskPageRequestVM requestVM);

    //通过分数等级获取
    List<TaskExam> getByGradeLevel(Integer gradeLevel);
}
