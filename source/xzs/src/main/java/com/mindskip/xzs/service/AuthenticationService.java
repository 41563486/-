package com.mindskip.xzs.service;

import com.mindskip.xzs.domain.User;


//认证服务
public interface AuthenticationService {

    /**
     * authUser
     *
     * @param username username
     * @param password password
     * @return boolean
     */
    boolean authUser(String username, String password);



    /**
     * authUser
     *
     * @param user     user
     * @param username username
     * @param password password
     * @return boolean
     */
    boolean authUser(User user, String username, String password);

    /**
     * pwdEncode 密码编码
     *
     * @param password password
     * @return String
     */
    String pwdEncode(String password);

    /**
     * pwdDecode 密码解码
     *
     * @param endodePwd endodePwd
     * @return String
     */
    String pwdDecode(String endodePwd);
}
