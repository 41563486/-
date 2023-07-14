package com.mindskip.xzs.viewmodel.admin.paper;

import com.mindskip.xzs.base.BasePage;

//试卷答案分页请求vm
public class ExamPaperAnswerPageRequestVM extends BasePage {
    private Integer subjectId;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
