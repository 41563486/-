package com.mind.zxks.controller.other.student;

import com.mind.zxks.base.RestResponse;
import com.mind.zxks.controller.other.BaseWXApiController;
import com.mind.zxks.domain.ExamPaper;
import com.mind.zxks.domain.Subject;
import com.mind.zxks.service.ExamPaperService;
import com.mind.zxks.service.SubjectService;
import com.mind.zxks.utility.DateTimeUtil;
import com.mind.zxks.utility.PageInfoHelper;
import com.mind.zxks.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.mind.zxks.viewmodel.student.exam.ExamPaperPageResponseVM;
import com.mind.zxks.viewmodel.student.exam.ExamPaperPageVM;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller("WXStudentExamController")
@RequestMapping(value = "/api/wx/student/exampaper")
@ResponseBody
public class ExamPaperController extends BaseWXApiController {

    private final ExamPaperService examPaperService;
    private final SubjectService subjectService;

    @Autowired
    public ExamPaperController(ExamPaperService examPaperService, SubjectService subjectService) {
        this.examPaperService = examPaperService;
        this.subjectService = subjectService;
    }


    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    public RestResponse<ExamPaperEditRequestVM> select(@PathVariable Integer id) {
        ExamPaperEditRequestVM vm = examPaperService.examPaperToVM(id);
        return RestResponse.ok(vm);
    }


    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamPaperPageResponseVM>> pageList(@Valid ExamPaperPageVM model) {
        model.setLevelId(getCurrentUser().getUserLevel());
        PageInfo<ExamPaper> pageInfo = examPaperService.studentPage(model);
        PageInfo<ExamPaperPageResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamPaperPageResponseVM vm = modelMapper.map(e, ExamPaperPageResponseVM.class);
            Subject subject = subjectService.selectById(vm.getSubjectId());
            vm.setSubjectName(subject.getName());
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }
}
