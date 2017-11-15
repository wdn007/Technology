package com.technology.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.technology.Model.UploadInfo;
import com.technology.Service.UploadService;
import com.technology.Utils.IdGenertor;

@Controller // 使用该注解标志它是一个控制器
@RequestMapping(value = "/Upload") // 用来处理请求地址映射，value指请求的实际地址
public class UploadController extends BaseController {
	@Autowired
	ServletContext context;
	@Autowired // 使用@Autowired进行自动装配，而不需要get/set方法
	public UploadService uploadService;

	@RequestMapping("/uploadFile")
	public String uploadRace(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 检测form是否是multipart/form-data类型的
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			throw new RuntimeException("The form's enctype attribute value must be multipart/form-data");
		}

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> fileList = multipartRequest.getFiles("attachment");

		UploadInfo upload = new UploadInfo();

		String username = (String) request.getSession().getAttribute("username");
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String folderName = df.format(now);
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(now);
		Date date = df.parse(str);
		upload.setUsername(username);
		upload.setUploadTime(date);
		upload.setStatus(new Integer(0));
		if (!fileList.isEmpty()) {
			MultipartFile file = fileList.get(0);
			if (file.getSize() != 0) {
				String UUID = IdGenertor.genGUID();
				String filePath = context.getRealPath("/upload") + File.separator + "attach" + File.separator
						+ folderName + File.separator + UUID + "_" + file.getOriginalFilename();
				File saveDir = new File(filePath);
				if (!saveDir.getParentFile().exists())
					saveDir.getParentFile().mkdirs();
				// 转存文件
				file.transferTo(saveDir);
				upload.setPath("upload/" + "attach" + '/' + folderName + '/' + UUID + "_" + file.getOriginalFilename());
			}
		}
		uploadService.save(upload);
		response.getWriter().write("<script>alert('文件上传成功！');");
		response.getWriter().write("window.parent.closeCurTab();</script>");
		return null;
	}

	@RequestMapping(value = "/showDownloadList")
	public String showDownloadList(HttpServletRequest request, HttpServletResponse response) {
		String curPage = request.getParameter("currentPage"); // 当前页码
		int currentPage = 1;

		String hql = "from UploadInfo as a order by a.uploadTime desc";

		if (curPage != null && !"".equals(curPage)) {
			currentPage = Integer.parseInt(curPage);
		}

		int recordCount = uploadService.count("select count(*) " + hql).intValue();
		int pageCount = (int) Math.ceil((float) recordCount / pageSize);

		List<UploadInfo> UploadInfoList = (List<UploadInfo>) uploadService.find(hql, currentPage, pageSize);
		request.setAttribute("UploadInfoList", UploadInfoList);
		request.setAttribute("recordCount", recordCount);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "downloadCenter";
	}

	@RequestMapping("/download")
	public String download(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		String path = request.getParameter("dloadfilename");
		if (path == null || "".equals(path) || path.indexOf('_') == -1)
			return null;
		String fileName = path.substring(path.indexOf('_') + 1);

		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		// 2.设置文件头
		String userAgent = request.getHeader("User-Agent");
		if (userAgent == null || "".equals(userAgent)) {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} else {
			if (userAgent.indexOf("MSIE") != -1) {
				// IE使用URLEncoder
				fileName = URLEncoder.encode(fileName, "UTF-8");
			} else {
				// FireFox使用ISO-8859-1
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}
		}
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		response.setDateHeader("Expires", (System.currentTimeMillis() + 1000));

		String realPath = context.getRealPath("/") + path;
		File file = new File(realPath);
		OutputStream out = null;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.close();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
		return null;
	}

	@RequestMapping(value = "/UncheckFileList")
	public String UncheckFileList(HttpServletRequest request, HttpServletResponse response) {
		String curPage = request.getParameter("currentPage"); // 当前页码
		int currentPage = 1;
		Integer b = new Integer(0);
		String hql = "from UploadInfo as a where a.status = " + b + " order by a.uploadTime desc";

		if (curPage != null && !"".equals(curPage)) {
			currentPage = Integer.parseInt(curPage);
		}

		int recordCount = uploadService.count("select count(*) " + hql).intValue();
		int pageCount = (int) Math.ceil((float) recordCount / pageSize);

		List<UploadInfo> UploadInfoList = (List<UploadInfo>) uploadService.find(hql, currentPage, pageSize);
		request.setAttribute("UploadInfoList", UploadInfoList);
		request.setAttribute("recordCount", recordCount);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "checkFile";
	}

	@RequestMapping("/checkFileInfo")
	public String checkFileInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("FileID");
		Integer b = new Integer(1);
		if (id != null && !"".equals(id)) {
			String hql = "update UploadInfo as file set file.status = ' " + b + " ' where file.id = '" + id + "'";
			if (uploadService.executeHql(hql) == 1) {
				response.getWriter().write("<script>alert('文件审核成功！');</script>");
			}
		} else {
			response.getWriter().write("<script>alert('参数错误！');");
		}
		return "redirect:/Upload/UncheckFileList";
	}
	
	@RequestMapping(value = "/myFileList")
	public String myFileList(HttpServletRequest request, HttpServletResponse response) {
		String curPage = request.getParameter("currentPage"); // 当前页码
		int currentPage = 1;
		String username = (String) request.getSession().getAttribute("username");
		String hql = "from UploadInfo as a where a.username = '" + username + "' order by a.uploadTime desc";

		if (curPage != null && !"".equals(curPage)) {
			currentPage = Integer.parseInt(curPage);
		}

		int recordCount = uploadService.count("select count(*) " + hql).intValue();
		int pageCount = (int) Math.ceil((float) recordCount / pageSize);

		List<UploadInfo> FileInfoList = (List<UploadInfo>) uploadService.find(hql, currentPage, pageSize);
		request.setAttribute("FileInfoList", FileInfoList);
		request.setAttribute("recordCount", recordCount);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "editFile";
	}
	
	@RequestMapping("/deleteFileInfo")
	public String deleteFileInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("FileID");
		if (id != null && !"".equals(id)) {
				uploadService.deleteById(id);
				response.getWriter().write("<script>alert('文件删除成功！');</script>");
		} else {
			response.getWriter().write("<script>alert('参数错误！');");
		}
		return "redirect:/Upload/myFileList";
	}

}
