package com.zmdj.demo.domain.res;

import lombok.Data;

/**
 * File upload result object
 * @author zhangyunyun create on 2019/2/16
 */
@Data
public class FileUploadResult {

    /**
     * the file's access url
     */
    private String url;

    /**
     * the name of the file
     */
    private String  name;

    /**
     * the file's size
     */
    private Integer fileSize;

    /**
     *  if file is image, the width of the image
     */
    private Integer width;


    /**
     * if file is image, the width of the image
     */
    private Integer height;

}
