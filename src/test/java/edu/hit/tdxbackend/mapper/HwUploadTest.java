package edu.hit.tdxbackend.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
public class HwUploadTest {
    private final UploadMapper uploadMapper;

    @Autowired
    public HwUploadTest(UploadMapper uploadMapper) {
        this.uploadMapper = uploadMapper;
    }

    @Test
    public void testUploadImage() throws FileNotFoundException {
         InputStream input = new FileInputStream("src/main/resources/assert/beach.png");
         String imageName = "test";
         String type = "category";
         uploadMapper.uploadImage(imageName, input, type);
    }
}
