package com.program.education.controller;

import com.program.education.entity.Page;
import com.program.education.entity.Video;
import com.program.education.entity.Work;
import com.program.education.service.WorkService;
import com.program.education.utils.HostHolder;
import com.program.education.utils.Message;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private WorkService workService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/workList", method = RequestMethod.GET)
    public String getWorkPage(Model model, Page page) {
        page.setRows(workService.selectAll(page.getoffset(), page.getLimit()).size());
        page.setPath("/work/workList");
        List<Work> works = workService.selectAll(page.getoffset(), page.getLimit());
        model.addAttribute("workList", works);
        return "site/work";
    }
    @RequestMapping(path = "/addWork", method = RequestMethod.GET)
    public String getAddWork(Model model) {
        return "site/addWork";
    }

    @RequestMapping(path = "/addWork", method = RequestMethod.POST)
    public String addVideo(@Param("title") String title, @Param("content") String content, @Param("file") MultipartFile file, HttpServletRequest request, Model model) throws IOException {
        Message message = new Message();
        Work work = new Work();
        if(file == null) {
            work.setTitle(title);
            work.setContent(content);
            work.setTeacher_id(1);
            work.setFile("null");
            work.setCreate_time(new Date());
            int result = workService.addWork(work);
            if(result == 0) {
                message.setCode(0);
                message.setMessage("上传失败！");
                return "redirect:/work/addWork";
            }
            message.setCode(1);
            message.setMessage("上传成功！");
            model.addAttribute("message", message);
            return "redirect:/work/workList";
        }
        try {
            // 保存文件
            byte[] bytes = file.getBytes();
            Path path = Paths.get("src/main/resources/static/teacherFile/" + file.getOriginalFilename());
            System.out.println(path);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        work.setTitle(title);
        work.setContent(content);
        work.setTeacher_id(1);
        work.setFile("src/main/resources/static/teacherFile/" + file.getOriginalFilename());
        work.setCreate_time(new Date());
        int result = workService.addWork(work);
        if(result == 0) {
            message.setCode(0);
            message.setMessage("上传失败！");
            return "redirect:/work/addWork";
        }
        message.setCode(1);
        message.setMessage("上传成功！");
        model.addAttribute("message", message);
        return "redirect:/work/workList";
    }

    @RequestMapping(path = "/uploadWork", method = RequestMethod.GET)
    public void uploadWork(@Param("id") int id, HttpServletResponse response, Model model) throws IOException {
        Work work = workService.selectById(id);
        String path = work.getFile();
        System.out.println(path);
        model.addAttribute("path", work.getFile());
        // 读到流中
        InputStream inputStream = new FileInputStream(path);// 文件的存放路径

        response.reset();
        response.setContentType("application/octet-stream");
        String filename = new File(path).getName();
        System.out.println(filename);
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] b = new byte[1024];
        int len;
        //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
        while ((len = inputStream.read(b)) > 0) {
            outputStream.write(b, 0, len);
        }
        inputStream.close();
    }

    @RequestMapping(path = "/deleteWork", method = RequestMethod.POST)
    @ResponseBody
    public String deleteWork(@Param("id") int id) {
        int result = workService.deleteById(id);
        return "success!";
    }
}
