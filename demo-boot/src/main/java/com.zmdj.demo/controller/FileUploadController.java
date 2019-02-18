package com.zmdj.demo.controller;

import com.zmdj.demo.domain.res.FileUploadResult;
import com.zmdj.demo.domain.res.Result;
import com.zmdj.demo.helper.ResultFactory;

import org.apache.commons.io.FilenameUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import lombok.Data;


/**
 * 文件上传
 * @author zhangyunyun create on 2019/2/16
 */
@RestController
public class FileUploadController {

    @RequestMapping("/upload.json")
    @ResponseBody
    public Result<List<FileUploadResult>> upload(
            /**
             * 参数名 file 是根据上传请求 body中文件的名称来决定的
             */
            @RequestParam(value = "file")MultipartFile[] files) throws IOException {

        List<FileUploadResult> upFileResList = new ArrayList<>();

        List<UploadFileVO> uploadFiles = pickFilesFormMultipartFiles(files);

        for (UploadFileVO uploadFile : uploadFiles) {

            upFileResList.add(persistentFile(uploadFile));

        }

        return ResultFactory.succeed(upFileResList);
    }

    /**
     * 将文件属性保存到临时对象中
     * @param files
     * @return
     * @throws IOException
     */
    private List<UploadFileVO> pickFilesFormMultipartFiles(MultipartFile[] files) throws IOException {

        List<UploadFileVO> uploadFileVOList = new ArrayList<>();

        for (MultipartFile file : files) {
            UploadFileVO uploadFileVO = new UploadFileVO();
            uploadFileVO.setFileName(file.getOriginalFilename());
            uploadFileVO.setExt(FilenameUtils.getExtension(file.getOriginalFilename()));
            uploadFileVO.setStream(file.getInputStream());
            uploadFileVO.setContentType(file.getContentType());
            uploadFileVO.setFileSize((int) file.getSize());
            uploadFileVOList.add(uploadFileVO);
        }

        return uploadFileVOList;
    }

    /**
     * 持久化文件
     * @param uploadFile
     * @return
     */
    private FileUploadResult persistentFile(UploadFileVO uploadFile) throws IOException {

        FileUploadResult fileUploadResult = new FileUploadResult();
        fileUploadResult.setFileSize(uploadFile.getFileSize());

        // BufferedInputStream 支持 mark() reset()
        BufferedInputStream bIn = new BufferedInputStream(uploadFile.getStream());

        String fileMd5 = null;
        //为了重复使用流，需要mark一下，下次用的时候reset
        bIn.mark(uploadFile.getFileSize());
        // 根据文件内容生成md5
        fileMd5 = DigestUtils.md5DigestAsHex(uploadFile.getStream());
        bIn.reset();

        // 如果是图片，则需要获取长宽
        if (uploadFile.isImage()) {
            bIn.mark(uploadFile.getFileSize());
            BufferedImage bufferedImage = ImageIO.read(uploadFile.getStream());
            fileUploadResult.setHeight(bufferedImage.getHeight());
            fileUploadResult.setWidth(bufferedImage.getWidth());
            bIn.reset();
        }

        String fileName = uploadFile.getFileName();
        if (!StringUtils.isEmpty(fileMd5)) {
            if (!StringUtils.isEmpty(uploadFile.getExt())) {
                fileName = String.format("%s.%s", fileMd5, uploadFile.getExt());
            }
        }

        String url = null;
        // save to filesystem
        // ...
        // set url value

        fileUploadResult.setName(fileName);
        fileUploadResult.setUrl(url);

        return fileUploadResult;
    }


    @Data
    class UploadFileVO {

        private InputStream stream;

        private String fileName;

        private String ext;

        private Integer fileSize;

        private String contentType;

        boolean isImage() {
            if (!StringUtils.isEmpty(contentType)) {
                return contentType.contains("image");
            }
            return false;
        }

    }

}
