package com.mind.zxks.domain.exam;


import java.util.List;

//试卷题目项目对象
public class ExamPaperTitleItemObject {

    private String name;

    private List<ExamPaperQuestionItemObject> questionItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExamPaperQuestionItemObject> getQuestionItems() {
        return questionItems;
    }

    public void setQuestionItems(List<ExamPaperQuestionItemObject> questionItems) {
        this.questionItems = questionItems;
    }
}
