package com.mind.zxks.repository;

import com.mind.zxks.domain.ExamPaperQuestionCustomerAnswer;
import com.mind.zxks.domain.other.ExamPaperAnswerUpdate;
import com.mind.zxks.domain.other.KeyValue;
import com.mind.zxks.viewmodel.student.question.answer.QuestionPageStudentRequestVM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

//用户试卷题目答案映射
@Mapper
public interface ExamPaperQuestionCustomerAnswerMapper extends BaseMapper<ExamPaperQuestionCustomerAnswer> {

    //根据答卷id查询
    List<ExamPaperQuestionCustomerAnswer> selectListByPaperAnswerId(Integer id);

    //学生页面
    List<ExamPaperQuestionCustomerAnswer> studentPage(QuestionPageStudentRequestVM requestVM);


    //数据入库
    int insertList(List<ExamPaperQuestionCustomerAnswer> list);

    //查询所有数量
    Integer selectAllCount();

    //根据时间查询数量
    List<KeyValue> selectCountByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    //更新分数
    int updateScore(List<ExamPaperAnswerUpdate> examPaperAnswerUpdates);
}
