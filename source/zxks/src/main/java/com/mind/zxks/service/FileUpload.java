package com.mind.zxks.service;


import java.io.InputStream;

//文件上传
public interface FileUpload {

    String uploadFile(InputStream inputStream, long size, String extName);

}
