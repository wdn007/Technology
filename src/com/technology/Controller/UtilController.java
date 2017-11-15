package com.technology.Controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.technology.Utils.IdGenertor;

@Controller
@RequestMapping(value = "/Util")
public class UtilController {
	@Autowired
	ServletContext context;

	@RequestMapping("/innerfileUpload.do")
	public String uploadPhoto(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> fileList = multipartRequest.getFiles("upload");
		String path = "";

		for (MultipartFile file : fileList) {
			String UUID = IdGenertor.genGUID();
			String filePath = context.getRealPath("/upload") + File.separator + "img" + File.separator + UUID + "_"
					+ file.getOriginalFilename();
			File saveDir = new File(filePath);
			if (!saveDir.getParentFile().exists())
				saveDir.getParentFile().mkdirs();
			// 转存文件
			file.transferTo(saveDir);
			path = "upload/" + "img" + '/' + UUID + "_" + file.getOriginalFilename();
			// CKEditor提交的很重要的一个参数——黄丽婷注
			String callback = request.getParameter("CKEditorFuncNum");
			// 返回"图像"选项卡并显示图片 request.getContextPath()为web项目名 两个不能合在一起否则出一些问题
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'../" + path + "');");
			out.println("</script>");
		}
		return null;
	}

}
