package com.mind.zxks.repository;

import com.mind.zxks.domain.UserToken;
import org.apache.ibatis.annotations.Mapper;

//用户token映射
@Mapper
public interface UserTokenMapper extends BaseMapper<UserToken> {

    //获取token
    UserToken getToken(String token);

}
