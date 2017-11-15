package com.technology.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.technology.Model.EntryFormInfo;
import com.technology.Model.RaceInfo;
import com.technology.Service.EntryFormService;
import com.technology.Service.RaceService;

@Controller // 使用该注解标志它是一个控制器
@RequestMapping(value = "/EntryForm") // 用来处理请求地址映射，value指请求的实际地址
public class EntryFormController extends BaseController {
	@Autowired
	ServletContext context;
	@Autowired // 使用@Autowired进行自动装配，而不需要get/set方法
	public EntryFormService entryFormService;
	@Autowired
	public RaceService raceService;

	@RequestMapping("/raceDetail")
	public String raceDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String raceID = request.getParameter("raceID");
		if (raceID != null && !"".equals(raceID)) {
			String hql = "from RaceInfo as race where race.raceID = '" + raceID + "'";
			RaceInfo raceinfo = raceService.getByHql(hql);
			if (raceinfo != null) {
				request.setAttribute("raceinfo", raceinfo);
				return "/entryDataForm";
			} else {
				response.getWriter()
						.write("<script>window.opener=null;window.open('','_self');window.close();</script>");
			}
		}
		return null;
	}

	@RequestMapping("/addEntryForm")
	public String addEntryForm(EntryFormInfo entryForm, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(now);
		Date date = df.parse(str);
		entryForm.setEntryDate(date);
//		String hql = "insert into EntryFormInfo as entry (raceID, leaderStuID, leaderStuName, stuID, stuName, "
//				+ "diretorTecName, entryDate) values (" + entryForm.getRaceID()
//				+ ", '" + entryForm.getLeaderStuID() + "', '" + entryForm.getLeaderStuName() + "', '"
//				+ entryForm.getStuID() + "', '" + entryForm.getStuName() + "', '" + entryForm.getDiretorTecName()
//				+ "', '" + entryForm.getEntryDate() + "')";
//		System.out.println(hql);
//		entryFormService.executeHql(hql);
		entryFormService.save(entryForm);
		response.getWriter().write("<script>alert('报名成功！');</script>");
		return "raceDetail";
	}

	@RequestMapping("/listEntryFormInfo")
	public String listEntryFormInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String curPage = request.getParameter("currentPage"); // 当前页码
		String queryTitle = request.getParameter("title");// 根据赛事名称查询
		String queryGrade = request.getParameter("grade"); // 根据赛事等级查询

		String uploadID = (String) request.getSession().getAttribute("ID");
		String hql = "from EntryFormInfo as entry where entry.raceID = any "
				+ "( select race.raceID from RaceInfo as race where race.uploadID = '" + uploadID + "' ";
		if (queryTitle != null && !"".equals(queryTitle)) {
			hql += "and race.title like '%" + queryTitle + "%' ";
		}
		if (queryGrade != null && !"".equals(queryGrade)) {
			hql += "and race.grade like '%" + queryGrade + "%' ";
		}
		hql += ") order by entry.entryDate desc";

		int currentPage = 1;
		if (curPage != null && !"".equals(curPage)) {
			currentPage = Integer.parseInt(curPage);
		}
		int recordCount = entryFormService.count("select count(*) " + hql).intValue();
		int pageCount = (int) Math.ceil((float) recordCount / pageSize);

		List<EntryFormInfo> entryFormInfoList = (List<EntryFormInfo>) entryFormService.find(hql, currentPage, pageSize);
		for (EntryFormInfo entry : entryFormInfoList) {
			String stuID = entry.getStuID();
			if (stuID != null)
				stuID = stuID.replace(",", "<br>");
			entry.setStuID(stuID);
			String stuName = entry.getStuName();
			if (stuName != null)
				stuName = stuName.replace(",", "<br>");
			entry.setStuName(stuName);
			String diretorTecName = entry.getDiretorTecName();
			if (diretorTecName != null)
				diretorTecName = diretorTecName.replace(",", "<br>");
			entry.setDiretorTecName(diretorTecName);
		}
		request.setAttribute("EntryFormInfoList", entryFormInfoList);
		request.setAttribute("recordCount", recordCount);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "entryCalculate";

	}

}
