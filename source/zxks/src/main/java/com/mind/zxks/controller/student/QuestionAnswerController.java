package com.mind.zxks.controller.student;

import com.mind.zxks.base.BaseApiController;
import com.mind.zxks.base.RestResponse;
import com.mind.zxks.domain.ExamPaperQuestionCustomerAnswer;
import com.mind.zxks.domain.Subject;
import com.mind.zxks.domain.TextContent;
import com.mind.zxks.domain.question.QuestionObject;
import com.mind.zxks.service.ExamPaperQuestionCustomerAnswerService;
import com.mind.zxks.service.QuestionService;
import com.mind.zxks.service.SubjectService;
import com.mind.zxks.service.TextContentService;
import com.mind.zxks.utility.DateTimeUtil;
import com.mind.zxks.utility.HtmlUtil;
import com.mind.zxks.utility.JsonUtil;
import com.mind.zxks.utility.PageInfoHelper;
import com.mind.zxks.viewmodel.admin.question.QuestionEditRequestVM;
import com.mind.zxks.viewmodel.student.exam.ExamPaperSubmitItemVM;
import com.mind.zxks.viewmodel.student.question.answer.QuestionAnswerVM;
import com.mind.zxks.viewmodel.student.question.answer.QuestionPageStudentRequestVM;
import com.mind.zxks.viewmodel.student.question.answer.QuestionPageStudentResponseVM;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("StudentQuestionAnswerController")
@RequestMapping(value = "/api/student/question/answer")
public class QuestionAnswerController extends BaseApiController {

    private final ExamPaperQuestionCustomerAnswerService examPaperQuestionCustomerAnswerService;
    private final QuestionService questionService;
    private final TextContentService textContentService;
    private final SubjectService subjectService;

    @Autowired
    public QuestionAnswerController(ExamPaperQuestionCustomerAnswerService examPaperQuestionCustomerAnswerService, QuestionService questionService, TextContentService textContentService, SubjectService subjectService) {
        this.examPaperQuestionCustomerAnswerService = examPaperQuestionCustomerAnswerService;
        this.questionService = questionService;
        this.textContentService = textContentService;
        this.subjectService = subjectService;
    }

    //通过ModelMapper映射bean属性
    //pageList: 这个方法处理分页获取学生问题答案的请求。接收一个 QuestionPageStudentRequestVM 对象，返回一个带有分页信息的学生问题答案列表。
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<QuestionPageStudentResponseVM>> pageList(@RequestBody QuestionPageStudentRequestVM model) {
        model.setCreateUser(getCurrentUser().getId());
        PageInfo<ExamPaperQuestionCustomerAnswer> pageInfo = examPaperQuestionCustomerAnswerService.studentPage(model);
        PageInfo<QuestionPageStudentResponseVM> page = PageInfoHelper.copyMap(pageInfo, q -> {
            Subject subject = subjectService.selectById(q.getSubjectId());
            QuestionPageStudentResponseVM vm = modelMapper.map(q, QuestionPageStudentResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(q.getCreateTime()));
            TextContent textContent = textContentService.selectById(q.getQuestionTextContentId());
            QuestionObject questionObject = JsonUtil.toJsonObject(textContent.getContent(), QuestionObject.class);
            String clearHtml = HtmlUtil.clear(questionObject.getTitleContent());
            vm.setShortTitle(clearHtml);
            vm.setSubjectName(subject.getName());
            return vm;
        });
        return RestResponse.ok(page);
    }

//根据id查询
    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    public RestResponse<QuestionAnswerVM> select(@PathVariable Integer id) {
        QuestionAnswerVM vm = new QuestionAnswerVM();
        ExamPaperQuestionCustomerAnswer examPaperQuestionCustomerAnswer = examPaperQuestionCustomerAnswerService.selectById(id);
        ExamPaperSubmitItemVM questionAnswerVM = examPaperQuestionCustomerAnswerService.examPaperQuestionCustomerAnswerToVM(examPaperQuestionCustomerAnswer);
        QuestionEditRequestVM questionVM = questionService.getQuestionEditRequestVM(examPaperQuestionCustomerAnswer.getQuestionId());
        vm.setQuestionVM(questionVM);
        vm.setQuestionAnswerVM(questionAnswerVM);
        return RestResponse.ok(vm);
    }

}
