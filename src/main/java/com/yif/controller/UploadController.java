package com.yif.controller;

import com.yif.utils.QiniuUtils;
import com.yif.vo.params.Result;
import com.yif.vo.params.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@Api(tags = "上传文件")
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private QiniuUtils qiniuUtils;

    @ApiOperation(value = "上传文件")
    @PostMapping("uploadImg")
    public Result Upload(@RequestParam MultipartFile file) {
        // 原始文件名称
        String originalFilename = file.getOriginalFilename();
        // 唯一的文件名称
        String fileName = UUID.randomUUID().toString() + "." +
                StringUtils.substringAfterLast(originalFilename, ".");
        // 上传文件 七牛云服务器 把图片发到服务器上
        // 降低自身服务器的带宽消耗

        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload) {
            log.info("上传成功：{}", fileName);
            return Result.success(QiniuUtils.url + "/" + fileName);
        }
        return Result.fail(ResultEnum.UPLOAD_FAILED);
    }
}
