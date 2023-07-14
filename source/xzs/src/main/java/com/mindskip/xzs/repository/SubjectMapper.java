package com.mindskip.xzs.repository;

import com.mindskip.xzs.domain.Subject;
import com.mindskip.xzs.viewmodel.admin.education.SubjectPageRequestVM;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//学科映射器
@Mapper
public interface SubjectMapper  extends BaseMapper<Subject> {

    //获取学科年级
    List<Subject> getSubjectByLevel(Integer level);

    //所有学科
    List<Subject> allSubject();

    //页面
    List<Subject> page(SubjectPageRequestVM requestVM);
}
