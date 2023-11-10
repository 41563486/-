package com.mind.zxks.event;

import com.mind.zxks.domain.ExamPaperAnswerInfo;
import org.springframework.context.ApplicationEvent;


//计算试卷答案完成事件
public class CalculateExamPaperAnswerCompleteEvent extends ApplicationEvent {


    private final ExamPaperAnswerInfo examPaperAnswerInfo;


    /**
     * Instantiates a new Calculate exam paper answer complete event.
     *
     * @param examPaperAnswerInfo the exam paper answer info
     */
    public CalculateExamPaperAnswerCompleteEvent(final ExamPaperAnswerInfo examPaperAnswerInfo) {
        super(examPaperAnswerInfo);
        this.examPaperAnswerInfo = examPaperAnswerInfo;
    }

    /**
     * Gets exam paper answer info.
     *
     * @return the exam paper answer info
     */
    public ExamPaperAnswerInfo getExamPaperAnswerInfo() {
        return examPaperAnswerInfo;
    }

}
