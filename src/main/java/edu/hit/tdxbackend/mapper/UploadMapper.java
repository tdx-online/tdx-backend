package edu.hit.tdxbackend.mapper;

import org.springframework.stereotype.Repository;

import java.io.InputStream;

//@Repository
public interface UploadMapper {
    /**
     * 上传图片
     */
    public String uploadImage(String imageName, InputStream input, String type);

    public void destroy();
}
