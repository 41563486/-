package com.mind.zxks.repository;

import com.mind.zxks.domain.ExamPaper;
import com.mind.zxks.domain.other.KeyValue;
import com.mind.zxks.viewmodel.admin.exam.ExamPaperPageRequestVM;
import com.mind.zxks.viewmodel.student.dashboard.PaperFilter;
import com.mind.zxks.viewmodel.student.dashboard.PaperInfo;
import com.mind.zxks.viewmodel.student.exam.ExamPaperPageVM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

//试卷页面映射
@Mapper
public interface ExamPaperMapper extends BaseMapper<ExamPaper> {

    //页面
    List<ExamPaper> page(ExamPaperPageRequestVM requestVM);

    //任务页面
    List<ExamPaper> taskExamPage(ExamPaperPageRequestVM requestVM);

    //学生页面
    List<ExamPaper> studentPage(ExamPaperPageVM requestVM);


    //索引
    List<PaperInfo> indexPaper(PaperFilter paperFilter);


    //查询所有数量
    Integer selectAllCount();

    //根据时间查询数量
    List<KeyValue> selectCountByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    //更新任务卷
    int updateTaskPaper(@Param("taskId") Integer taskId,@Param("paperIds") List<Integer> paperIds);

    //清除任务卷
    int clearTaskPaper(@Param("paperIds") List<Integer> paperIds);
}
