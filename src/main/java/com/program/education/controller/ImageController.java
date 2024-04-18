package com.program.education.controller;

import com.program.education.entity.Image;
import com.program.education.service.ImageService;
import com.program.education.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/imageList", method = RequestMethod.GET)
    public String getImage(Model model) {
        List<Image> images = imageService.selectAll();
        model.addAttribute("images", images);
        return "site/image";
    }

    @RequestMapping(path = "/addImage", method = RequestMethod.POST)
    public String addImage(MultipartFile file) throws Exception{
        if(file.isEmpty()) {
            throw new RuntimeException("上传文件不能为空");
        }
        System.out.println(file.getName() + "--" + file.getOriginalFilename() + "--" + file.getContentType() + "--" + file.getResource());
        InputStream in = file.getInputStream();
        long name = System.currentTimeMillis();
        FileOutputStream out= new FileOutputStream("src/main/resources/static/images/" + name + ".jpg");
        BufferedInputStream bufferedIn=new BufferedInputStream(in);
        BufferedOutputStream bufferedOut=new BufferedOutputStream(out);
        byte[] by=new byte[1];
        while (bufferedIn.read(by)!=-1) {
            bufferedOut.write(by);
        }
        bufferedOut.flush();
        bufferedIn.close();
        bufferedOut.close();
        Image image = new Image();
        String url = name + ".jpg";
        image.setUrl(url);
        image.setCreate_time(new Date());
        image.setStatus(1);
        imageService.addImage(image);
        return "redirect:/image/imageList";
    }

}
