package com.loto.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-08-05 23:41</p>
 * <p>PageName：MultipartController.java</p>
 * <p>Function：SpringMVC 处理 multipart 形式的数据（文件上传）</p>
 */

@Controller
@RequestMapping("multipart")
public class MultipartController {
    /**
     * 文件上传
     */
    @RequestMapping(value = "/upload")
    public ModelAndView upload(MultipartFile uploadFile, HttpSession session) throws IOException {
        // 获取原始名称
        String originalFilename = uploadFile.getOriginalFilename();

        // 获取扩展名
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());

        // 重命名
        String newName = UUID.randomUUID().toString() + "." + ext;

        // 存储到指定的文件夹，/uploads/yyyy-MM-dd，考虑文件过多的情况按照日期，生成一个子文件夹
        String realPath = session.getServletContext().getRealPath("/uploads");
        String datePath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File folder = new File(realPath + "/" + datePath);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        // 存储文件到目录
        uploadFile.transferTo(new File(folder, newName));

        // TODO 文件磁盘路径要更新到数据库字段

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date", date);
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
