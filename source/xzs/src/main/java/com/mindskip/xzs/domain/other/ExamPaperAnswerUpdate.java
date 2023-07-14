package com.mindskip.xzs.domain.other;


//试卷答案更新
public class ExamPaperAnswerUpdate {
    private Integer id;
    //评分
    private Integer customerScore;
    //是否做正确了
    private Boolean doRight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerScore() {
        return customerScore;
    }

    public void setCustomerScore(Integer customerScore) {
        this.customerScore = customerScore;
    }

    public Boolean getDoRight() {
        return doRight;
    }

    public void setDoRight(Boolean doRight) {
        this.doRight = doRight;
    }
}
