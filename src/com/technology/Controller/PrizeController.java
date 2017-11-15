package com.technology.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.technology.Model.PrizeInfo;
import com.technology.Service.PrizeService;

@Controller // 使用该注解标志它是一个控制器
@RequestMapping(value = "/Prize") // 用来处理请求地址映射，value指请求的实际地址
public class PrizeController {
	@Autowired // 使用@Autowired进行自动装配，而不需要get/set方法
	public PrizeService prizeService;

	@RequestMapping("/addPrizeForm")
	public String addPrizeForm(PrizeInfo prizeInfo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(now);
		Date date = df.parse(str);
		prizeInfo.setUploadTime(date);

		String ID = (String) request.getSession().getAttribute("ID");
		String username = (String) request.getSession().getAttribute("username");
		prizeInfo.setUploadID(ID);
		prizeInfo.setUploadUsername(username);

		prizeService.save(prizeInfo);
		response.getWriter().write("<script>alert('获奖信息发布成功！');");
		response.getWriter().write("window.parent.closeCurTab();</script>");
		return null;
	}

	@RequestMapping("/prizeSearchInfo")
	public String prizeSearchInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userID = request.getParameter("userID"); // 根据学号查询
		String username = request.getParameter("username"); // 根据姓名查询
		String title = request.getParameter("title"); // 根据赛事名称查询
		String prizeType = request.getParameter("prizeType"); // 根据获奖类型查询
		boolean hasPara = false;

		String hql = "from PrizeInfo as prize ";
		if (userID != null && !"".equals(userID)) {
			hql += "where prize.userID like '%" + userID + "%' ";
			hasPara = true;
		}
		if (username != null && !"".equals(username)) {
			if (!hasPara) {
				hql += "where prize.username = '" + username + "' ";
			} else {
				hql += "and prize.username = '" + username + "' ";
				hasPara = true;
			}
		}
		if (title != null && !"".equals(title)) {
			if (!hasPara) {
				hql += "where prize.title = '" + title + "' ";
			} else {
				hql += "and prize.title = '" + title + "' ";
				hasPara = true;
			}
		}
		if (prizeType != null && !"".equals(prizeType)) {
			if (!hasPara) {
				hql += "where prize.prizeType = '" + prizeType + "' ";
			} else {
				hql += "and prize.prizeType = '" + prizeType + "' ";
				hasPara = true;
			}
		}
		hql += "order by prize.prizeType , prize.userID asc, prize.uploadTime desc";
		List<PrizeInfo> prize = prizeService.find(hql);
		request.setAttribute("prizeinfo", prize);
		return "prizeSearch";
	}

}
