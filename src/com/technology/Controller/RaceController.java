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
import com.technology.Model.RaceInfo;
import com.technology.Service.RaceService;
import com.technology.Utils.IdGenertor;

@Controller // 使用该注解标志它是一个控制器
@RequestMapping(value = "/Race") // 用来处理请求地址映射，value指请求的实际地址
public class RaceController extends BaseController {
	@Autowired
	ServletContext context;
	@Autowired // 使用@Autowired进行自动装配，而不需要get/set方法
	public RaceService raceService;

	@RequestMapping("/list")
	public ModelAndView goList() {
		return new ModelAndView("race/list");
	}

	@RequestMapping("/listRaceInfo")
	public String listRaceInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);// 默认值为1
		int rows = ServletRequestUtils.getIntParameter(request, "rows", 0);

		String uploadID = (String) request.getSession().getAttribute("ID");
		if (uploadID == null || "".equals(uploadID)) {
			response.getWriter().write("<script>alert('登录信息已过期，请先登录！');");
			response.getWriter().write("top.location.href = '../View/login.jsp';</script>");
			return null;
		}

		String queryUploadTimeS = request.getParameter("queryUploadTimeS");// 获取要查询的竞赛申请时间
		String queryUploadTimeE = request.getParameter("queryUploadTimeE");
		String queryGrade = request.getParameter("queryGrade");// 获取要查询的竞赛等级
		String querySponsor = request.getParameter("querySponsor");// 获取要查询的竞赛主办单位

		Grid grid = new Grid();
		String hql = "from RaceInfo as race where race.uploadID = '" + uploadID + "' ";
		if (queryUploadTimeS != null && !"".equals(queryUploadTimeS)) {
			if (queryUploadTimeE != null && !"".equals(queryUploadTimeE)) {
				hql += "and race.uploadTime between '" + queryUploadTimeS + " 00:00:00' and '" + queryUploadTimeE
						+ " 23:59:59' ";
			} else {
				hql += "and race.uploadTime like '%" + queryUploadTimeS + "%' ";
			}
		}
		if (queryGrade != null && !"".equals(queryGrade)) {
			hql += "and race.grade like '%" + queryGrade + "%' ";
		}
		if (querySponsor != null && !"".equals(querySponsor)) {
			hql += "and race.sponsor = '" + querySponsor + "' ";
		}
		hql += "order by race.uploadTime";

		List<RaceInfo> race = (List<RaceInfo>) raceService.find(hql, page, rows);
		for (RaceInfo raceInfo : race) {
			String accessory = raceInfo.getAccessory();
			if (accessory != null && !"".equals(accessory) && accessory.indexOf("_") != -1)
				raceInfo.setAccessory(accessory.substring(accessory.indexOf("_") + 1));
		}
		grid.setTotal(raceService.count("select count(*) " + hql));
		grid.setRows(race);
		writeJson(grid, response);
		return null;
	}

	@RequestMapping("/listRaceInfoUncheck")
	public String listRaceInfoUncheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);// 默认值为1
		int rows = ServletRequestUtils.getIntParameter(request, "rows", 0);

		String userID = (String) request.getSession().getAttribute("ID");
		if (userID == null || "".equals(userID)) {
			response.getWriter().write("<script>alert('登录信息已过期，请先登录！');");
			response.getWriter().write("top.location.href = '../View/login.jsp';</script>");
			return null;
		}

		String queryUploadTimeS = request.getParameter("queryUploadTimeS");// 获取要查询的竞赛申请时间
		String queryUploadTimeE = request.getParameter("queryUploadTimeE");
		String queryGrade = request.getParameter("queryGrade");// 获取要查询的竞赛等级
		String querySponsor = request.getParameter("querySponsor");// 获取要查询的竞赛主办单位

		Grid grid = new Grid();
		String hql = "from RaceInfo as race where race.applyStatus = 0";
		if (queryUploadTimeS != null && !"".equals(queryUploadTimeS)) {
			if (queryUploadTimeE != null && !"".equals(queryUploadTimeE)) {
				hql += "and race.uploadTime between '" + queryUploadTimeS + " 00:00:00' and '" + queryUploadTimeE
						+ " 23:59:59' ";
			} else {
				hql += "and race.uploadTime like '%" + queryUploadTimeS + "%' ";
			}
		}
		if (queryGrade != null && !"".equals(queryGrade)) {
			hql += "and race.grade like '%" + queryGrade + "%' ";
		}
		if (querySponsor != null && !"".equals(querySponsor)) {
			hql += "and race.sponsor = '" + querySponsor + "' ";
		}
		hql += "order by race.uploadTime";

		List<RaceInfo> race = (List<RaceInfo>) raceService.find(hql, page, rows);
		for (RaceInfo raceInfo : race) {
			String accessory = raceInfo.getAccessory();
			if (accessory != null && !"".equals(accessory) && accessory.indexOf("_") != -1)
				raceInfo.setAccessory(accessory.substring(accessory.indexOf("_") + 1));
		}
		grid.setTotal(raceService.count("select count(*) " + hql));
		grid.setRows(race);
		writeJson(grid, response);
		return null;
	}

	@RequestMapping("/findRaceInfo")
	public String findRaceInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String raceID = request.getParameter("raceID");
		if (raceID != null && !"".equals(raceID)) {
			String hql = "from RaceInfo as race where race.raceID = '" + raceID + "'";
			RaceInfo raceInfo = raceService.getByHql(hql);
			if (raceInfo != null) {
				String information = raceInfo.getInformation();
				information = information.replace("src=\"./upload/img/", "src=\"../upload/img/");
				raceInfo.setInformation(information);
				request.setAttribute("raceInfo", raceInfo);
				return "/raceEdit";
			} else {
				response.getWriter().write("<script>alert('竞赛信息不存在！');");
			}
		} else {
			response.getWriter().write("<script>alert('参数错误！');");
		}
		response.getWriter().write("window.parent.closeCurTab();</script>");
		return null;
	}

	@RequestMapping("/prizeUpload")
	public String prizeUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String raceID = request.getParameter("raceID");
		if (raceID != null && !"".equals(raceID)) {
			String hql = "from RaceInfo as race where race.raceID = '" + raceID + "'";
			RaceInfo raceInfo = raceService.getByHql(hql);
			if (raceInfo != null) {
				request.setAttribute("raceInfo", raceInfo);
				return "/prizeUpload";
			} else {
				response.getWriter().write("<script>alert('竞赛信息不存在！');");
				response.getWriter().write("window.parent.closeCurTab();</script>");
			}
		} else {
			response.getWriter().write("<script>alert('参数错误！');");
			response.getWriter().write("window.parent.closeCurTab();</script>");
		}
		return null;
	}

	@RequestMapping("/checkRaceInfo")
	public String checkRaceInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String raceID = request.getParameter("raceID");
		if (raceID != null && !"".equals(raceID)) {
			String hql = "from RaceInfo as race where race.raceID = '" + raceID + "'";
			RaceInfo raceInfo = raceService.getByHql(hql);
			if (raceInfo != null) {
				if (raceInfo.getApplyStatus() == 0) {
					request.setAttribute("raceInfo", raceInfo);
					return "/raceCheck";
				}
				response.getWriter().write("<script>alert('该竞赛已审核！');");

			} else {
				response.getWriter().write("<script>alert('竞赛信息不存在！');");
			}
		} else {
			response.getWriter().write("<script>alert('参数错误！');");
		}
		response.getWriter().write("window.parent.closeCurTab();</script>");
		return null;
	}

	@RequestMapping("/saveRaceCheck")
	public String saveRaceCheck(RaceInfo race, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String raceID = race.getRaceID();
		if (raceID != null && !"".equals(raceID)) {
			Integer status = race.getApplyStatus();
			if (status != null && status == 1) {
				String hql = "update RaceInfo as race set race.applyStatus = 1, race.grade = '" + race.getGrade()
						+ "' where race.raceID = '" + raceID + "'";
				if (raceService.executeHql(hql) == 1) {
					response.getWriter().write("<script>alert('竞赛信息审核成功！');</script>");
				}
			}
		} else {
			response.getWriter().write("<script>alert('参数错误！');</script>");
		}
		response.getWriter().write("<script>window.parent.closeCurTab();</script>");
		return null;
	}

	@RequestMapping("/raceDetail")
	public String raceDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String raceID = request.getParameter("raceID");
		if (raceID != null && !"".equals(raceID)) {
			String hql = "from RaceInfo as race where race.raceID = '" + raceID + "'";
			RaceInfo raceinfo = raceService.getByHql(hql);
			if (raceinfo != null) {
				hql = "update RaceInfo as race set race.visitCount = race.visitCount + 1 where race.raceID = '" + raceID
						+ "'";
				raceService.executeHql(hql);
				raceinfo.setVisitCount(raceinfo.getVisitCount() + 1);
				request.setAttribute("raceinfo", raceinfo);
				return "/raceDetail";
			} else {
				response.getWriter()
						.write("<script>window.opener=null;window.open('','_self');window.close();</script>");
			}
		}
		return null;
	}

	@RequestMapping("/updateRaceInfo")
	public String updateRaceInfo(RaceInfo race, HttpServletRequest request, HttpServletResponse response)
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
		race.setUploadID(uploadID);
		race.setUploadUsername(username);
		race.setUploadTime(date);
		race.setApplyStatus(new Integer(0));
		String information = race.getInformation();
		information = information.replace("src=\"../upload/img/", "src=\"./upload/img/");
		race.setInformation(information);
		String oldAccessory = race.getAccessory();
		if (oldAccessory != null && oldAccessory.startsWith("-")) {
			String filePath = context.getRealPath("/upload") + oldAccessory.substring(7);
			filePath.replace('/', '\\');
			File file = new File(filePath);
			if (file.exists() && file.isFile())
				file.delete();
			race.setAccessory("");
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
				race.setAccessory("upload/" + "attach" + '/' + folderName + '/' + UUID + "_"
						+ multipartFile.getOriginalFilename());
			}
		}
		raceService.update(race);
		response.getWriter().write("<script>alert('竞赛信息修改成功！');");
		response.getWriter().write("window.parent.closeCurTab();</script>");
		return null;
	}

	@RequestMapping("/uploadRace")
	public String uploadRace(RaceInfo race, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		race.setUploadID(uploadID);
		race.setUploadUsername(username);
		race.setUploadTime(date);
		race.setApplyStatus(new Integer(0));
		race.setVisitCount(new Long(0));
		String information = race.getInformation();
		information = information.replace("src=\"../upload/img/", "src=\"./upload/img/");
		race.setInformation(information);
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
				race.setAccessory(
						"upload/" + "attach" + '/' + folderName + '/' + UUID + "_" + file.getOriginalFilename());
			}
		}
		raceService.save(race);
		response.getWriter().write("<script>alert('竞赛信息发布成功！');");
		response.getWriter().write("window.parent.closeCurTab();</script>");
		return null;
	}

	@RequestMapping("/removeRaceInfo")
	public String removeRaceInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Json json = new Json();// 用于向前端发送消息
		String raceID = request.getParameter("raceID");
		try {
			RaceInfo race = raceService.getAcademyInfoByAcademy(raceID);
			String accessory = race.getAccessory();
			if (accessory != null && !"".equals(accessory)) {
				String filePath = context.getRealPath("/upload") + accessory.substring(6);
				filePath.replace('/', '\\');
				File file = new File(filePath);
				if (file.exists() && file.isFile())
					file.delete();
			}
			raceService.delete(race);
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

	@RequestMapping(value = "/showRaceList")
	public String showRaceList(HttpServletRequest request, HttpServletResponse response) {
		String curPage = request.getParameter("currentPage"); // 当前页码
		int currentPage = 1;
		Integer a = new Integer(1);
		String hql = "from RaceInfo as race where race.applyStatus='" + a + "' order by race.uploadTime desc";

		if (curPage != null && !"".equals(curPage)) {
			currentPage = Integer.parseInt(curPage);
		}

		int recordCount = raceService.count("select count(*) " + hql).intValue();
		int pageCount = (int) Math.ceil((float) recordCount / pageSize);

		List<RaceInfo> RaceInfoList = (List<RaceInfo>) raceService.find(hql, currentPage, pageSize);
		request.setAttribute("RaceInfoList", RaceInfoList);
		request.setAttribute("recordCount", recordCount);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "raceList";
	}

}
