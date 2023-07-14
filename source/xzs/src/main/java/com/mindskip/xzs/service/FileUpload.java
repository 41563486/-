package com.mindskip.xzs.service;


import java.io.InputStream;
import java.util.Set;

//文件上传
public interface FileUpload {

    String uploadFile(InputStream inputStream, long size, String extName);

}
