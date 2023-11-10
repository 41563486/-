package com.mind.zxks.service;

import com.mind.zxks.domain.ExamPaperQuestionCustomerAnswer;
import com.mind.zxks.domain.other.ExamPaperAnswerUpdate;
import com.mind.zxks.viewmodel.student.exam.ExamPaperSubmitItemVM;
import com.mind.zxks.viewmodel.student.question.answer.QuestionPageStudentRequestVM;
import com.github.pagehelper.PageInfo;

import java.util.List;


//客户试卷题目答案
public interface ExamPaperQuestionCustomerAnswerService extends BaseService<ExamPaperQuestionCustomerAnswer> {

    //学生分页
    PageInfo<ExamPaperQuestionCustomerAnswer> studentPage(QuestionPageStudentRequestVM requestVM);

    //根据试卷答案id查询
    List<ExamPaperQuestionCustomerAnswer> selectListByPaperAnswerId(Integer id);

    /**
     * 试卷提交答案入库
     *
     * @param examPaperQuestionCustomerAnswers List<ExamPaperQuestionCustomerAnswer>
     */
    void insertList(List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers);

    /**
     * 试卷问题答题信息转成ViewModel 传给前台
     *
     * @param qa ExamPaperQuestionCustomerAnswer
     * @return ExamPaperSubmitItemVM
     */
    ExamPaperSubmitItemVM examPaperQuestionCustomerAnswerToVM(ExamPaperQuestionCustomerAnswer qa);


    Integer selectAllCount();

    List<Integer> selectMothCount();

    int updateScore(List<ExamPaperAnswerUpdate> examPaperAnswerUpdates);
}
