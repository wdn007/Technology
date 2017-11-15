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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.technology.Model.Grid;
import com.technology.Model.Json;
import com.technology.Model.NewsInfo;
import com.technology.Service.NewsService;
import com.technology.Utils.IdGenertor;

@Controller // 使用该注解标志它是一个控制器
@RequestMapping(value = "/News") // 用来处理请求地址映射，value指请求的实际地址
public class NewsController extends BaseController {
	@Autowired
	ServletContext context;
	@Autowired // 使用@Autowired进行自动装配，而不需要get/set方法
	public NewsService newsService;

	@RequestMapping("/list")
	public ModelAndView goList() {
		return new ModelAndView("news/list");
	}

	@RequestMapping("/listNewsInfo")
	public String listNewsInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);// 默认值为1
		int rows = ServletRequestUtils.getIntParameter(request, "rows", 0);

		String queryNewsTimeS = request.getParameter("queryNewsTimeS");// 获取要查询的发布新闻的开始日期
		String queryNewsTimeE = request.getParameter("queryNewsTimeE");
		String queryAcademy = request.getParameter("queryAcademy");// 获取要查询的发布新闻的学院
		String uploadID = (String) request.getSession().getAttribute("ID");
		if (uploadID == null || "".equals(uploadID)) {
			response.getWriter().write("<script>alert('登录信息已过期，请先登录！');");
			response.getWriter().write("top.location.href = '../View/login.jsp';</script>");
			return null;
		}

		Grid grid = new Grid();

		String hql = "from NewsInfo as news where news.uploadID = '" + uploadID + "' ";
		if (queryNewsTimeS != null && !"".equals(queryNewsTimeS)) {
			if (queryNewsTimeE != null && !"".equals(queryNewsTimeE)) {
				hql += "and news.newsTime between '" + queryNewsTimeS + " 00:00:00' and '" + queryNewsTimeE
						+ " 23:59:59' ";
			} else {
				hql += "and news.newsTime like '%" + queryNewsTimeS + "%' ";
			}
		}
		if (queryAcademy != null && !"".equals(queryAcademy)) {
			hql += "and news.academy = '" + queryAcademy + "' ";
		}
		hql += "order by news.newsTime";
		List<NewsInfo> news = (List<NewsInfo>) newsService.find(hql, page, rows);
		for (NewsInfo newsInfo : news) {
			String accessory = newsInfo.getAccessory();
			if (accessory != null && !"".equals(accessory) && accessory.indexOf("_") != -1)
				newsInfo.setAccessory(accessory.substring(accessory.indexOf("_") + 1));
		}
		grid.setTotal(newsService.count("select count(*) " + hql));
		grid.setRows(news);
		writeJson(grid, response);
		return null;
	}

	@RequestMapping("/listNewsInfoUncheck")
	public String listNewsInfoUncheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);// 默认值为1
		int rows = ServletRequestUtils.getIntParameter(request, "rows", 0);

		String userID = (String) request.getSession().getAttribute("ID");
		if (userID == null || "".equals(userID)) {
			response.getWriter().write("<script>alert('登录信息已过期，请先登录！');");
			response.getWriter().write("top.location.href = '../View/login.jsp';</script>");
			return null;
		}

		String queryNewsTimeS = request.getParameter("queryNewsTimeS");// 获取要查询的发布新闻的开始日期
		String queryNewsTimeE = request.getParameter("queryNewsTimeE");
		String queryAcademy = request.getParameter("queryAcademy");// 获取要查询的发布新闻的学院

		Grid grid = new Grid();
		
		String hql = "from NewsInfo as news where news.status = 0";
		if (queryNewsTimeS != null && !"".equals(queryNewsTimeS)) {
			if (queryNewsTimeE != null && !"".equals(queryNewsTimeE)) {
				hql += "and news.newsTime between '" + queryNewsTimeS + " 00:00:00' and '" + queryNewsTimeE
						+ " 23:59:59' ";
			} else {
				hql += "and news.newsTime like '%" + queryNewsTimeS + "%' ";
			}
		}
		if (queryAcademy != null && !"".equals(queryAcademy)) {
			hql += "and news.academy = '" + queryAcademy + "' ";
		}
		hql += "order by news.newsTime";

		List<NewsInfo> news = (List<NewsInfo>) newsService.find(hql, page, rows);
		for (NewsInfo newsInfo : news) {
			String accessory = newsInfo.getAccessory();
			if (accessory != null && !"".equals(accessory) && accessory.indexOf("_") != -1)
				newsInfo.setAccessory(accessory.substring(accessory.indexOf("_") + 1));
		}
		grid.setTotal(newsService.count("select count(*) " + hql));
		grid.setRows(news);
		writeJson(grid, response);
		return null;
	}

	@RequestMapping("/findNewsInfo")
	public String findNewsInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String newsID = request.getParameter("newsID");
		if (newsID != null && !"".equals(newsID)) {
			String hql = "from NewsInfo as news where news.newsID = '" + newsID + "'";
			NewsInfo newsInfo = newsService.getByHql(hql);
			if (newsInfo != null) {
				String detail = newsInfo.getDetail();
				detail = detail.replace("src=\"./upload/img/", "src=\"../upload/img/");
				newsInfo.setDetail(detail);
				request.setAttribute("newsInfo", newsInfo);
				return "/newsEdit";
			} else {
				response.getWriter().write("<script>alert('新闻信息不存在！');");
			}
		} else {
			response.getWriter().write("<script>alert('参数错误！');");
		}
		response.getWriter().write("window.parent.closeCurTab();</script>");
		return null;
	}

	@RequestMapping("/checkNewsInfo")
	public String checkNewsInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String newsID = request.getParameter("newsID");
		if (newsID != null && !"".equals(newsID)) {
			String hql = "from NewsInfo as news where news.newsID = '" + newsID + "'";
			NewsInfo newsInfo = newsService.getByHql(hql);
			if (newsInfo != null) {
				if (newsInfo.getStatus() == 0) {
					request.setAttribute("newsInfo", newsInfo);
					return "/newsCheck";
				}
				response.getWriter().write("<script>alert('该新闻已审核！');");
			} else {
				response.getWriter().write("<script>alert('新闻信息不存在！');");
			}
		} else {
			response.getWriter().write("<script>alert('参数错误！');");
		}
		response.getWriter().write("window.parent.closeCurTab();</script>");
		return null;
	}

	@RequestMapping("/saveNewsCheck")
	public String saveNewsCheck(NewsInfo news, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String newsID = news.getNewsID();
		if (newsID != null && !"".equals(newsID)) {
			Integer status = news.getStatus();
			if (status != null && status == 1) {
				String hql = "update NewsInfo as news set news.status = 1 where news.newsID = '" + newsID + "'";
				if (newsService.executeHql(hql) == 1) {
					response.getWriter().write("<script>alert('新闻审核成功！');</script>");
				}
			}
		} else {
			response.getWriter().write("<script>alert('参数错误！');</script>");
		}
		response.getWriter().write("<script>window.parent.closeCurTab();</script>");
		return null;
	}

	@RequestMapping("/newsDetail")
	public String newsDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String newsID = request.getParameter("newsID");
		if (newsID != null && !"".equals(newsID)) {
			String hql = "from NewsInfo as news where news.newsID = '" + newsID + "'";
			NewsInfo newsinfo = newsService.getByHql(hql);
			if (newsinfo != null) {
				hql = "update NewsInfo as news set news.visitCount = news.visitCount + 1 where news.newsID = '" + newsID
						+ "'";
				newsService.executeHql(hql);
				newsinfo.setVisitCount(newsinfo.getVisitCount() + 1);
				request.setAttribute("newsinfo", newsinfo);
				return "/newsDetail";
			} else {
				response.getWriter()
						.write("<script>window.opener=null;window.open('','_self');window.close();</script>");
			}
		}
		return null;
	}

	@RequestMapping("/updateNewsInfo")
	public String updateNewsInfo(NewsInfo news, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 检测form是否是multipart/form-data类型的
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			throw new RuntimeException("The form's enctype attribute value must be multipart/form-data");
		}

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> fileList = multipartRequest.getFiles("attachment");

		String uploadID = (String) request.getSession().getAttribute("ID");
		if (uploadID == null || "".equals(uploadID)) {
			response.getWriter().write("<script>alert('登录信息已过期，请先登录！');");
			response.getWriter().write("top.location.href = '../View/login.jsp';</script>");
			return null;
		}
		String username = (String) request.getSession().getAttribute("username");
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String folderName = df.format(now);
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(now);
		Date date = df.parse(str);
		news.setUploadID(uploadID);
		news.setUsername(username);
		news.setNewsTime(date);
		news.setStatus(new Integer(0));
		//news.setVisitCount(new Long(0));
		String detail = news.getDetail();
		detail = detail.replace("src=\"./upload/img/", "src=\"../upload/img/");
		news.setDetail(detail);
		String oldAccessory = news.getAccessory();
		if (oldAccessory != null && oldAccessory.startsWith("-")) {
			String filePath = context.getRealPath("/upload") + oldAccessory.substring(7);
			filePath.replace('/', '\\');
			File file = new File(filePath);
			if (file.exists() && file.isFile())
				file.delete();
			news.setAccessory("");
		}
		if (!fileList.isEmpty()) {
			MultipartFile multipartFile = fileList.get(0);
			if (multipartFile.getSize() != 0) {
				if (oldAccessory != null && !"".equals(oldAccessory)) {
					String filePath = context.getRealPath("/upload") + oldAccessory.substring(6);
					filePath.replace('/', '\\');
					File file = new File(filePath);
					if (file.exists() && file.isFile())
						file.delete();
				}
				String UUID = IdGenertor.genGUID();
				String filePath = context.getRealPath("/upload") + File.separator + "attach" + File.separator
						+ folderName + File.separator + UUID + "_" + multipartFile.getOriginalFilename();
				File saveDir = new File(filePath);
				if (!saveDir.getParentFile().exists())
					saveDir.getParentFile().mkdirs();
				// 转存文件
				multipartFile.transferTo(saveDir);
				news.setAccessory("upload/" + "attach" + '/' + folderName + '/' + UUID + "_"
						+ multipartFile.getOriginalFilename());
			}
		}
		newsService.update(news);
		response.getWriter().write("<script>alert('新闻修改成功！');");
		response.getWriter().write("window.parent.closeCurTab();</script>");
		return null;
	}

	@RequestMapping("/uploadNewsInfo")
	public String uploadNews(NewsInfo news, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 检测form是否是multipart/form-data类型的
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			throw new RuntimeException("The form's enctype attribute value must be multipart/form-data");
		}

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> fileList = multipartRequest.getFiles("attachment");

		String username = (String) request.getSession().getAttribute("username");
		String uploadID = (String) request.getSession().getAttribute("ID");
		if (uploadID == null || "".equals(uploadID)) {
			response.getWriter().write("<script>alert('登录信息已过期，请先登录！');");
			response.getWriter().write("top.location.href = '../View/login.jsp';</script>");
			return null;
		}

		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String folderName = df.format(now);
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(now);
		Date date = df.parse(str);
		news.setUploadID(uploadID);
		news.setUsername(username);
		news.setNewsTime(date);
		news.setStatus(new Integer(0));
		news.setVisitCount(new Long(0));
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
				news.setAccessory(
						"upload/" + "attach" + '/' + folderName + '/' + UUID + "_" + file.getOriginalFilename());
			}
		}
		newsService.save(news);
		response.getWriter().write("<script>alert('新闻信息发布成功！');");
		response.getWriter().write("window.parent.closeCurTab();</script>");
		return null;
	}

	@RequestMapping("/addNewsInfo")
	public String addNewsInfo(NewsInfo user, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Json json = new Json();// 用于向前端发送消息
		String hql = "from NewsInfo as user where user.newsID = '" + user.getNewsID() + "'";
		if (newsService.getByHql(hql) != null) {
			json.setMsg("新建失败，新闻已存在！");
		} else {
			newsService.save(user);
			json.setMsg("新建成功！");
			json.setSuccess(true);
		}
		writeJson(json, response);
		return null;
	}

	@RequestMapping("/removeNewsInfo")
	public String removeNewsInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Json json = new Json();// 用于向前端发送消息
		String newsID = request.getParameter("newsID");
		try {
			newsService.delete(newsService.getAcademyInfoByAcademy(newsID));
			json.setMsg("删除成功！");
			json.setSuccess(true);
			writeJson(json, response);
			return null;
		} catch (Exception e) {
			json.setMsg("删除失败！" + e.getMessage());
			writeJson(json, response);
			return null;
		}
	}

	@RequestMapping("/download")
	public String download(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		String path = request.getParameter("dloadfilename");
		if (path == null || "".equals(path) || path.indexOf('_') == -1) {
			return null;
		}
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

	@RequestMapping(value = "/showNewsList")
	public String showNewsList(HttpServletRequest request, HttpServletResponse response) {
		String curPage = request.getParameter("currentPage"); // 当前页码
		int currentPage = 1;
		Integer a = new Integer(1);
		String hql = "from NewsInfo as newsinfo where newsinfo.status=" + a + "  order by newsinfo.newsTime desc";

		if (curPage != null && !"".equals(curPage)) {
			currentPage = Integer.parseInt(curPage);
		}

		int recordCount = newsService.count("select count(*) " + hql).intValue();
		int pageCount = (int) Math.ceil((float) recordCount / pageSize);

		List<NewsInfo> NewsInfoList = (List<NewsInfo>) newsService.find(hql, currentPage, pageSize);
		request.setAttribute("NewsInfoList", NewsInfoList);
		request.setAttribute("recordCount", recordCount);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "newsList";
	}

}
