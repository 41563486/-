package com.mindskip.xzs.repository;

import com.mindskip.xzs.domain.ExamPaperAnswer;
import com.mindskip.xzs.domain.other.KeyValue;
import com.mindskip.xzs.viewmodel.student.exampaper.ExamPaperAnswerPageVM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

//持久层映射类
@Mapper
public interface ExamPaperAnswerMapper extends BaseMapper<ExamPaperAnswer> {

    //学生页面
    List<ExamPaperAnswer> studentPage(ExamPaperAnswerPageVM requestVM);

    //查总数
    Integer selectAllCount();

    //根据时间查数量
    //开始时间和结束时间
    List<KeyValue> selectCountByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    //根据进程id或者用户id获取
    ExamPaperAnswer getByPidUid(@Param("pid") Integer paperId, @Param("uid") Integer uid);

    //管理员页面
    List<ExamPaperAnswer> adminPage(com.mindskip.xzs.viewmodel.admin.paper.ExamPaperAnswerPageRequestVM requestVM);
}
