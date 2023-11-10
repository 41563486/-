package com.mind.zxks.controller.student;

import com.mind.zxks.base.BaseApiController;
import com.mind.zxks.base.RestResponse;
import com.mind.zxks.domain.*;
import com.mind.zxks.domain.enums.ExamPaperAnswerStatusEnum;
import com.mind.zxks.event.CalculateExamPaperAnswerCompleteEvent;
import com.mind.zxks.event.UserEvent;
import com.mind.zxks.service.ExamPaperAnswerService;
import com.mind.zxks.service.ExamPaperService;
import com.mind.zxks.service.SubjectService;
import com.mind.zxks.utility.DateTimeUtil;
import com.mind.zxks.utility.ExamUtil;
import com.mind.zxks.utility.PageInfoHelper;
import com.mind.zxks.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.mind.zxks.viewmodel.student.exam.ExamPaperReadVM;
import com.mind.zxks.viewmodel.student.exam.ExamPaperSubmitVM;
import com.mind.zxks.viewmodel.student.exampaper.ExamPaperAnswerPageResponseVM;
import com.mind.zxks.viewmodel.student.exampaper.ExamPaperAnswerPageVM;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

//学生试卷答案控制器
@RestController("StudentExamPaperAnswerController")
@RequestMapping(value = "/api/student/exampaper/answer")
public class ExamPaperAnswerController extends BaseApiController {

    private final ExamPaperAnswerService examPaperAnswerService;
    private final ExamPaperService examPaperService;
    private final SubjectService subjectService;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public ExamPaperAnswerController(ExamPaperAnswerService examPaperAnswerService, ExamPaperService examPaperService, SubjectService subjectService, ApplicationEventPublisher eventPublisher) {
        this.examPaperAnswerService = examPaperAnswerService;
        this.examPaperService = examPaperService;
        this.subjectService = subjectService;
        this.eventPublisher = eventPublisher;
    }


    //页面数据封装
    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamPaperAnswerPageResponseVM>> pageList(@RequestBody @Valid ExamPaperAnswerPageVM model) {
        model.setCreateUser(getCurrentUser().getId());
        PageInfo<ExamPaperAnswer> pageInfo = examPaperAnswerService.studentPage(model);
        PageInfo<ExamPaperAnswerPageResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamPaperAnswerPageResponseVM vm = modelMapper.map(e, ExamPaperAnswerPageResponseVM.class);
            Subject subject = subjectService.selectById(vm.getSubjectId());
            vm.setDoTime(ExamUtil.secondToVM(e.getDoTime()));
            vm.setSystemScore(ExamUtil.scoreToVM(e.getSystemScore()));
            vm.setUserScore(ExamUtil.scoreToVM(e.getUserScore()));
            vm.setPaperScore(ExamUtil.scoreToVM(e.getPaperScore()));
            vm.setSubjectName(subject.getName());
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }


    //试卷提交
    @RequestMapping(value = "/answerSubmit", method = RequestMethod.POST)
    public RestResponse answerSubmit(@RequestBody @Valid ExamPaperSubmitVM examPaperSubmitVM) {
        User user = getCurrentUser();
        ExamPaperAnswerInfo examPaperAnswerInfo = examPaperAnswerService.calculateExamPaperAnswer(examPaperSubmitVM, user);
        if (null == examPaperAnswerInfo) {
            return RestResponse.fail(2, "试卷不能重复做");
        }
        ExamPaperAnswer examPaperAnswer = examPaperAnswerInfo.getExamPaperAnswer();
        Integer userScore = examPaperAnswer.getUserScore();
        String scoreVm = ExamUtil.scoreToVM(userScore);
        UserEventLog userEventLog = new UserEventLog(user.getId(), user.getUserName(), user.getRealName(), new Date());
        String content = user.getUserName() + " 提交试卷：" + examPaperAnswerInfo.getExamPaper().getName()
                + " 得分：" + scoreVm
                + " 耗时：" + ExamUtil.secondToVM(examPaperAnswer.getDoTime());
        userEventLog.setContent(content);
        eventPublisher.publishEvent(new CalculateExamPaperAnswerCompleteEvent(examPaperAnswerInfo));
        eventPublisher.publishEvent(new UserEvent(userEventLog));
        return RestResponse.ok(scoreVm);
    }


    //编辑
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RestResponse edit(@RequestBody @Valid ExamPaperSubmitVM examPaperSubmitVM) {
        boolean notJudge = examPaperSubmitVM.getAnswerItems().stream().anyMatch(i -> i.getDoRight() == null && i.getScore() == null);
        if (notJudge) {
            return RestResponse.fail(2, "有未批改题目");
        }

        ExamPaperAnswer examPaperAnswer = examPaperAnswerService.selectById(examPaperSubmitVM.getId());
        ExamPaperAnswerStatusEnum examPaperAnswerStatusEnum = ExamPaperAnswerStatusEnum.fromCode(examPaperAnswer.getStatus());
        if (examPaperAnswerStatusEnum == ExamPaperAnswerStatusEnum.Complete) {
            return RestResponse.fail(3, "试卷已完成");
        }
        String score = examPaperAnswerService.judge(examPaperSubmitVM);
        User user = getCurrentUser();
        UserEventLog userEventLog = new UserEventLog(user.getId(), user.getUserName(), user.getRealName(), new Date());
        String content = user.getUserName() + " 批改试卷：" + examPaperAnswer.getPaperName() + " 得分：" + score;
        userEventLog.setContent(content);
        eventPublisher.publishEvent(new UserEvent(userEventLog));
        return RestResponse.ok(score);
    }

    //读取
    @RequestMapping(value = "/read/{id}", method = RequestMethod.POST)
    public RestResponse<ExamPaperReadVM> read(@PathVariable Integer id) {
        ExamPaperAnswer examPaperAnswer = examPaperAnswerService.selectById(id);
        ExamPaperReadVM vm = new ExamPaperReadVM();
        ExamPaperEditRequestVM paper = examPaperService.examPaperToVM(examPaperAnswer.getExamPaperId());
        ExamPaperSubmitVM answer = examPaperAnswerService.examPaperAnswerToVM(examPaperAnswer.getId());
        vm.setPaper(paper);
        vm.setAnswer(answer);
        return RestResponse.ok(vm);
    }


}
