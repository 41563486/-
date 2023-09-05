package com.mindskip.xzs.base;


import com.mindskip.xzs.context.WebContext;
import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.utility.ModelMapperSingle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseApiController {
    /**
     * The constant DEFAULT_PAGE_SIZE.
     */
    protected final static String DEFAULT_PAGE_SIZE = "10";
    /**
     * The constant modelMapper.
     */
    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();
    /**
     * The Web context.
     */
    @Autowired
    protected WebContext webContext;

    /**
     * Gets current user.
     *
     * @return the current user
     */
    protected User getCurrentUser() {
        return webContext.getCurrentUser();
    }
}
