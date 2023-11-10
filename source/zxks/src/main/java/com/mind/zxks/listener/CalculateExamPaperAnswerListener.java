package com.mind.zxks.listener;

import com.mind.zxks.domain.*;
import com.mind.zxks.domain.enums.ExamPaperTypeEnum;
import com.mind.zxks.domain.enums.QuestionTypeEnum;
import com.mind.zxks.event.CalculateExamPaperAnswerCompleteEvent;
import com.mind.zxks.service.ExamPaperAnswerService;
import com.mind.zxks.service.ExamPaperQuestionCustomerAnswerService;
import com.mind.zxks.service.TaskExamCustomerAnswerService;
import com.mind.zxks.service.TextContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


//计算考试答案监听器
@Component
public class CalculateExamPaperAnswerListener implements ApplicationListener<CalculateExamPaperAnswerCompleteEvent> {

    private final ExamPaperAnswerService examPaperAnswerService;
    private final ExamPaperQuestionCustomerAnswerService examPaperQuestionCustomerAnswerService;
    private final TextContentService textContentService;
    private final TaskExamCustomerAnswerService examCustomerAnswerService;

    /**
     * Instantiates a new Calculate exam paper answer listener.
     *
     * @param examPaperAnswerService                 the exam paper answer service
     * @param examPaperQuestionCustomerAnswerService the exam paper question customer answer service
     * @param textContentService                     the text content service
     * @param examCustomerAnswerService              the exam customer answer service
     */

    @Autowired
    public CalculateExamPaperAnswerListener(ExamPaperAnswerService examPaperAnswerService, ExamPaperQuestionCustomerAnswerService examPaperQuestionCustomerAnswerService, TextContentService textContentService, TaskExamCustomerAnswerService examCustomerAnswerService) {
        this.examPaperAnswerService = examPaperAnswerService;
        this.examPaperQuestionCustomerAnswerService = examPaperQuestionCustomerAnswerService;
        this.textContentService = textContentService;
        this.examCustomerAnswerService = examCustomerAnswerService;
    }

    @Override
    //事务管理注解
    @Transactional
    //事件触发
    public void onApplicationEvent(CalculateExamPaperAnswerCompleteEvent calculateExamPaperAnswerCompleteEvent) {
        Date now = new Date();
        //获取触发对象的信息
        ExamPaperAnswerInfo examPaperAnswerInfo = (ExamPaperAnswerInfo) calculateExamPaperAnswerCompleteEvent.getSource();
        //获取对象试卷
        ExamPaper examPaper = examPaperAnswerInfo.getExamPaper();
        //获取试卷的答案
        ExamPaperAnswer examPaperAnswer = examPaperAnswerInfo.getExamPaperAnswer();
        List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers = examPaperAnswerInfo.getExamPaperQuestionCustomerAnswers();
        examPaperAnswerService.insertByFilter(examPaperAnswer);
        //将用户的试卷答案转换成流然后进行数据过滤，将答案存入数据层，并使用foreach（可使用lambda表示式）对数据进行处理
        examPaperQuestionCustomerAnswers.stream().filter(a -> QuestionTypeEnum.needSaveTextContent(a.getQuestionType())).forEach(d -> {
            //获取文本内容的答案和当前时间
            TextContent textContent = new TextContent(d.getAnswer(), now);
            //将文本内定答案存入数据层
            textContentService.insertByFilter(textContent);
            d.setTextContentId(textContent.getId());
            //清空
            d.setAnswer(null);
        });
        //给每个答案设置id
        examPaperQuestionCustomerAnswers.forEach(d -> {
            d.setExamPaperAnswerId(examPaperAnswer.getId());
        });
        //答案入库
        examPaperQuestionCustomerAnswerService.insertList(examPaperQuestionCustomerAnswers);
        //遍历出任务试卷
        switch (ExamPaperTypeEnum.fromCode(examPaper.getPaperType())) {
            case Task: {
                examCustomerAnswerService.insertOrUpdate(examPaper, examPaperAnswer, now);
                break;
            }
            default:
                break;
        }
    }
}
