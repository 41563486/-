package com.mind.zxks.service;

import com.mind.zxks.domain.ExamPaper;
import com.mind.zxks.domain.ExamPaperAnswer;
import com.mind.zxks.domain.TaskExamCustomerAnswer;

import java.util.Date;
import java.util.List;

//用户考试任务答案服务
public interface TaskExamCustomerAnswerService extends BaseService<TaskExamCustomerAnswer> {

    void insertOrUpdate(ExamPaper examPaper, ExamPaperAnswer examPaperAnswer, Date now);

    TaskExamCustomerAnswer selectByTUid(Integer tid, Integer uid);

    List<TaskExamCustomerAnswer> selectByTUid(List<Integer> taskIds, Integer uid);
}
