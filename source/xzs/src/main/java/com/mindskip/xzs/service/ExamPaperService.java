package com.mindskip.xzs.service;

import com.mindskip.xzs.domain.ExamPaper;
import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.mindskip.xzs.viewmodel.admin.exam.ExamPaperPageRequestVM;
import com.mindskip.xzs.viewmodel.student.dashboard.PaperFilter;
import com.mindskip.xzs.viewmodel.student.dashboard.PaperInfo;
import com.mindskip.xzs.viewmodel.student.exam.ExamPaperPageVM;
import com.github.pagehelper.PageInfo;

import java.util.List;


//试卷
public interface ExamPaperService extends BaseService<ExamPaper> {

    //分页
    PageInfo<ExamPaper> page(ExamPaperPageRequestVM requestVM);


    //任务考试分页
    PageInfo<ExamPaper> taskExamPage(ExamPaperPageRequestVM requestVM);

    //学生分页
    PageInfo<ExamPaper> studentPage(ExamPaperPageVM requestVM);

    //通过视图保存试卷
    ExamPaper savePaperFromVM(ExamPaperEditRequestVM examPaperEditRequestVM, User user);

    //试卷转换成视图
    ExamPaperEditRequestVM examPaperToVM(Integer id);

    //页面索引
    List<PaperInfo> indexPaper(PaperFilter paperFilter);


    Integer selectAllCount();

    List<Integer> selectMothCount();
}
