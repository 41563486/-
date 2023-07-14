package com.mindskip.xzs.viewmodel.admin.education;

import com.mindskip.xzs.base.BasePage;


//学科分页视图模块
public class SubjectPageRequestVM extends BasePage {
    private Integer id;
    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
