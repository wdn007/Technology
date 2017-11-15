package com.technology.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.technology.Model.AcademyInfo;
import com.technology.Model.Grid;
import com.technology.Model.Json;
import com.technology.Service.AcademyService;

//@SessionAttributes({ "username", "identity" })
@Controller // 使用该注解标志它是一个控制器
@RequestMapping(value = "/Academy") // 用来处理请求地址映射，value指请求的实际地址
public class AcademyController extends BaseController {
	@Autowired // 使用@Autowired进行自动装配，而不需要get/set方法
	public AcademyService academyService;

	@RequestMapping("/list")
	public ModelAndView goList() {
		return new ModelAndView("academy/list");
	}

	@RequestMapping("/listAcademyInfo")
	public String listAcademyInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);// 默认值为1
		int rows = ServletRequestUtils.getIntParameter(request, "rows", 0);
		String queryId = request.getParameter("queryID");// 获取要查询的用户账号
		Grid grid = new Grid();
		String hql;
		List<AcademyInfo> users;
		if (queryId != null) {
			hql = "from AcademyInfo as user where user.id like '%" + queryId + "%'";
		} else {
			hql = "from AcademyInfo";
		}
		users = (List<AcademyInfo>) academyService.find(hql, page, rows);
		grid.setTotal(academyService.count("select count(*) " + hql));
		grid.setRows(users);
		writeJson(grid, response);
		return null;
	}

	@RequestMapping("/addAcademyInfo")
	public String addAcademyInfo(AcademyInfo user, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Json json = new Json();// 用于向前端发送消息
		String hql = "from AcademyInfo as user where user.academy = '" + user.getAcademy() + "'";
		if (academyService.getByHql(hql) != null) {
			json.setMsg("新建用户失败，该用户已存在！");
		} else {
			academyService.save(user);
			json.setMsg("新建用户成功！");
			json.setSuccess(true);
		}
		writeJson(json, response);
		return null;
	}

	@RequestMapping("/removeAcademyInfo")
	public String removeAcademyInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Json json = new Json();// 用于向前端发送消息
		String academy = request.getParameter("academy");
		try {
			academyService.delete(academyService.getAcademyInfoByAcademy(academy));
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

	@RequestMapping("/updateAcademyInfo")
	public String updateAcademyInfo(AcademyInfo user, HttpServletResponse response) throws Exception {
		Json json = new Json();// 用于向前端发送消息
		String hql = "from AcademyInfo as user where user.academy = '" + user.getAcademy() + "'";
		if (academyService.getByHql(hql) == null) {
			json.setMsg("更新失败，学院不存在！");
		} else {
			academyService.update(user);
			json.setMsg("更新成功！");
			json.setSuccess(true);
		}
		writeJson(json, response);
		return null;
	}

}
