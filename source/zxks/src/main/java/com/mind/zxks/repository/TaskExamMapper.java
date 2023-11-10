package com.mind.zxks.repository;

import com.mind.zxks.domain.TaskExam;
import com.mind.zxks.viewmodel.admin.task.TaskPageRequestVM;
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
