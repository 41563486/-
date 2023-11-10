package com.mind.zxks.controller;

import com.mind.zxks.base.SystemCode;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//错误页面控制器
@RestController
public class ErrorController extends BasicErrorController {

    private static final String PATH = "/error";

    public ErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    //指定返回json格式的请求
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})

    //返回错误信息和状态码
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> error = new HashMap<>(2);
        error.put("code", SystemCode.InnerError.getCode());
        error.put("message", SystemCode.InnerError.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
