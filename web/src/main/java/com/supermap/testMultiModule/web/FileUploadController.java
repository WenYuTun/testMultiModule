package com.supermap.testMultiModule.web;

import com.supermap.testMultiModule.ov.ResponseJsonData;
import com.supermap.testMultiModule.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;


@RestController
@RequestMapping("/upload")
public class FileUploadController {
    /**
     * 上传文件路径
     */
    @Value("${fileupload.path}")
    private String fileuploadPath;


    /**
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public ResponseJsonData testUploadFile(@RequestParam("file") MultipartFile file,
                                           HttpServletRequest request) throws Exception {
        //首先进行文件上传
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        final String realPath = request.getSession().getServletContext().getRealPath("/tmp");
        FileUtils.uploadFile(file.getBytes(), fileuploadPath, fileName, contentType);
        return ResponseJsonData.ok(realPath);
    }

}
