package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadController {
	//1��ʾ�ϴ��ı���
	@RequestMapping("/showUpload.do")
	public String showUpload() {
		return "upload";
	}
	//2ʵ���ϴ��ļ��Ĺ���
	@RequestMapping("/uploadFile.do")
	public String uploadFile(MultipartFile file) throws Exception {
		//ʵ���ϴ�����
		file.transferTo(new File("d:/",file.getOriginalFilename()));
		//��Ӧҳ��
		return "redirect:../main/showIndex.do";
	}
}

















