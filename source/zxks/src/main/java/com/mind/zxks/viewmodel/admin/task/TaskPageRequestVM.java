package com.mind.zxks.viewmodel.admin.task;

import com.mind.zxks.base.BasePage;



//任务分页请求vm
public class TaskPageRequestVM extends BasePage {
    private Integer gradeLevel;

    public Integer getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(Integer gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
}
