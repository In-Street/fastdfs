package cyf.fast.fdfs.controller;

import cyf.fast.fdfs.service.FdfsService;
import cyf.fast.util.FastfdfsFileutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017-5-25.
 */

@Controller
public class FastController {

    @Autowired
    FdfsService fdfsService;

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public String get() {
        fdfsService.get();
        System.out.println();
        return "";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        String name = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        long size = multipartFile.getSize();
        byte[] bytes = multipartFile.getBytes();
          String path = fdfsService.upload("group1", inputStream, size, name, bytes);
        //String path = FastfdfsFileutil.uploadFile(bytes, name);
        System.out.println(path);
        return "";
    }
}
