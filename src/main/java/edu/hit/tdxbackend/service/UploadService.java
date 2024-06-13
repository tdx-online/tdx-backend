package edu.hit.tdxbackend.service;

import java.io.InputStream;

public interface UploadService {
    boolean uploadImage(String imageName, InputStream inputStream, String type, Integer pid);
}