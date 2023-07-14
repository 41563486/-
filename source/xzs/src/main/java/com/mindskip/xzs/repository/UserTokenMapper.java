package com.mindskip.xzs.repository;

import com.mindskip.xzs.domain.UserToken;
import org.apache.ibatis.annotations.Mapper;

//用户token映射
@Mapper
public interface UserTokenMapper extends BaseMapper<UserToken> {

    //获取token
    UserToken getToken(String token);

}
