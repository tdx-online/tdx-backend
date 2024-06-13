package edu.hit.tdxbackend.controller;

import edu.hit.tdxbackend.entity.ResultInfo;
import edu.hit.tdxbackend.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/upload")
public class UploadController {
    private final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/product")
    @ResponseBody
    public ResultInfo product(@RequestParam("filename") String filename,
                              @RequestParam("file") MultipartFile file,
                              @RequestParam("type") String type,
                              @RequestParam("pid") int pid) {
        System.out.println("product");
        ResultInfo info = new ResultInfo();
        try {
            InputStream inputStream = file.getInputStream();
            info.setFlag(uploadService.uploadImage(filename, inputStream, type, pid));
        } catch (NumberFormatException e) {
            info.setFlag(false);
            info.setErrorMsg("请输入正确的商品id");
        } catch (IOException e) {
            info.setFlag(false);
            info.setErrorMsg("上传失败");
//            e.printStackTrace();
        }
        return info;
    }

    @PostMapping("/category")
    @ResponseBody
    public ResultInfo category(@RequestParam("filename") String filename,
                               @RequestParam("file") MultipartFile file,
                               @RequestParam("id") int id) {
        System.out.println("category");
        ResultInfo info = new ResultInfo();
        try {
            InputStream inputStream = file.getInputStream();
            info.setFlag(uploadService.uploadImage(filename, inputStream, "category", id));
        } catch (NumberFormatException e) {
            info.setFlag(false);
            info.setErrorMsg("请输入正确的商品分类id");
        } catch (IOException e) {
            info.setFlag(false);
            info.setErrorMsg("上传失败");
//            e.printStackTrace();
        }
        return info;
    }
}