package com.mind.zxks.service;

import com.mind.zxks.domain.TaskExam;
import com.mind.zxks.domain.User;
import com.mind.zxks.viewmodel.admin.task.TaskPageRequestVM;
import com.mind.zxks.viewmodel.admin.task.TaskRequestVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

//任务考试服务
public interface TaskExamService extends BaseService<TaskExam> {

    PageInfo<TaskExam> page(TaskPageRequestVM requestVM);

    void edit(TaskRequestVM model, User user);

    TaskRequestVM taskExamToVM(Integer id);

    List<TaskExam> getByGradeLevel(Integer gradeLevel);
}
