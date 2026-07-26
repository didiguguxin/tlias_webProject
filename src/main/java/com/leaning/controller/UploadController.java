package com.leaning.controller;

import com.leaning.pojo.Result;
import com.leaning.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
/*
    public Result upload(String name,Integer age, MultipartFile file) throws IOException {//文件接收
        log.info("文件上传开始"+name+age+ file);

        //文件本地存储
        String originalFilename = file.getOriginalFilename();
        String fileType=originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() +fileType;
        file.transferTo(new File("D:/pictures/" + newFileName));

         return Result.success();
}
 */

        //文件上传到阿里云OSS
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传文件：{}", file);
        if (!file.isEmpty()) {
            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + extName;
            // 上传文件
            String url = aliyunOSSOperator.upload(file.getBytes(), uniqueFileName);
            return Result.success(url);
        }
        return Result.error("上传失败");
    }


     }




