package com.loto.project.domain;

import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * 验证码
 */
@Data
public class ImageCode {

    /**
     * 验证码图片
     */
    private BufferedImage image;

    /**
     * code验证码
     */
    private String code;


    /**
     * 构造函数
     *
     * @param image
     * @param code
     */
    public ImageCode(BufferedImage image, String code) {
        this.image = image;
        this.code = code;
    }

}