package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadController {
	//1显示上传的表单
	@RequestMapping("/showUpload.do")
	public String showUpload() {
		return "upload";
	}
	//2实现上传文件的功能
	@RequestMapping("/uploadFile.do")
	public String uploadFile(MultipartFile file) throws Exception {
		//实现上传功能
		file.transferTo(new File("d:/",file.getOriginalFilename()));
		//响应页面
		return "redirect:../main/showIndex.do";
	}
}


















