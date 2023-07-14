package com.mindskip.xzs.utility;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * @version 3.5.0
 * @description: The type Model mapper single.
 * Copyright (C), 2020-2021, 武汉思维跳跃科技有限公司
 * @date 2021/12/25 9:45
 */
//模块映射单例
public class ModelMapperSingle {
    /**
     * The constant modelMapper.
     */
    protected final static ModelMapper modelMapper = new ModelMapper();
    private final static ModelMapperSingle modelMapperSingle = new ModelMapperSingle();

    static {
        //要求全完全类型匹配
        modelMapper.getConfiguration().setFullTypeMatchingRequired(true);
        //属性类型和名称完全匹配才进行复制
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Instance model mapper.
     *
     * @return the model mapper
     */
    public static ModelMapper Instance() {
        return modelMapperSingle.modelMapper;
    }
}
