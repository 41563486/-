package com.mind.zxks.domain.enums;

import java.util.HashMap;
import java.util.Map;

//试卷状态答案枚举
public enum ExamPaperAnswerStatusEnum {

    WaitJudge(1, "待批改"),
    Complete(2, "完成");

    int code;
    String name;

    ExamPaperAnswerStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private static final Map<Integer, ExamPaperAnswerStatusEnum> keyMap = new HashMap<>();

    //存放状态
    static {
        for (ExamPaperAnswerStatusEnum item : ExamPaperAnswerStatusEnum.values()) {
            keyMap.put(item.getCode(), item);
        }
    }

    //返回状态码
    public static ExamPaperAnswerStatusEnum fromCode(Integer code) {
        return keyMap.get(code);
    }

}
