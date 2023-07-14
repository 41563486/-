package com.mindskip.xzs.repository;

//基础映射
public interface BaseMapper<T> {

    //根据主键删除
    int deleteByPrimaryKey(Integer id);

    //插入 类型为泛型
    int insert(T record);

    //选择性插入 类型为泛型
    int insertSelective(T record);

    //根据主键查询
    T selectByPrimaryKey(Integer id);

    //根据主键选择性更新
    int updateByPrimaryKeySelective(T record);

    //根据主键更新
    int updateByPrimaryKey(T record);
}
