package com.technology.Controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.technology.Model.Grid;
import com.technology.Model.Json;
import com.technology.Model.TecInfo;
import com.technology.Service.TecService;

//@SessionAttributes({ "username", "identity" })
@Controller // 使用该注解标志它是一个控制器
@RequestMapping(value = "/Teacher") // 用来处理请求地址映射，value指请求的实际地址
public class TeacherController extends BaseController {
	@Autowired // 使用@Autowired进行自动装配，而不需要get/set方法
	public TecService tecService;

	@RequestMapping("/list")
	public ModelAndView goList() {
		return new ModelAndView("user/list");
	}

	@RequestMapping("/listTecInfo")
	public String listTecInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);// 默认值为1
		int rows = ServletRequestUtils.getIntParameter(request, "rows", 0);
		String queryId = request.getParameter("queryID");// 获取要查询的用户账号
		String queryName = request.getParameter("queryName");// 获取要查询的用户姓名
		Grid grid = new Grid();
		String hql;
		List<TecInfo> users;
		if ((queryId != null && !"".equals(queryId)) && (queryName != null && !"".equals(queryName))) {
			hql = "from TecInfo as user where user.jobID like '%" + queryId + "%'" + " and " + "user.username='"
					+ queryName + "'";
		} else if ((queryId != null && !"".equals(queryId)) && (queryName == null || "".equals(queryName))) {
			hql = "from TecInfo as user where user.jobID like '%" + queryId + "%'";
		} else if ((queryId == null || "".equals(queryId)) && (queryName != null && !"".equals(queryName))) {
			hql = "from TecInfo as user where user.username like '%" + queryName + "%'";
		} else {
			hql = "from TecInfo";
		}
		users = (List<TecInfo>) tecService.find(hql, page, rows);
		grid.setTotal(tecService.count("select count(*) " + hql));
		grid.setRows(users);
		writeJson(grid, response);
		return null;
	}

	@RequestMapping("/listTecInfoSingle")
	public String listTecInfoSingle(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession)
			throws Exception {
		String ID = (String) request.getSession().getAttribute("ID");
		String hql = "from TecInfo as user where user.jobID = '" + ID + "'";
		TecInfo teacherSingle = tecService.find(hql).get(0);
		httpSession.setAttribute("teacherSingle", teacherSingle);
		return "teacherSingle";
	}

	@RequestMapping("/addTecInfo")
	public String addTecInfo(TecInfo user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Json json = new Json();// 用于向前端发送消息
		String hql = "from TecInfo as user where user.username = '" + user.getUsername() + "'";
		if (tecService.getByHql(hql) != null) {
			json.setMsg("新建用户失败，该用户已存在！");
		} else {
			tecService.save(user);
			json.setMsg("新建用户成功！");
			json.setSuccess(true);
		}
		writeJson(json, response);
		return null;
	}

	@RequestMapping("/removeTecInfo")
	public String removeTecInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Json json = new Json();// 用于向前端发送消息
		String userId = request.getParameter("jobID");
		try {
			tecService.delete(tecService.getTecInfoById(userId));
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

	@RequestMapping("/updateTecInfo")
	public String updateTecInfo(TecInfo user, HttpServletResponse response) throws Exception {
		Json json = new Json();// 用于向前端发送消息
		String hql = "from TecInfo as user where user.jobID = '" + user.getJobID() + "'";
		if (tecService.getByHql(hql) == null) {
			json.setMsg("更新失败，工号不存在！");
		} else {
			tecService.update(user);
			json.setMsg("更新成功！");
			json.setSuccess(true);
		}
		writeJson(json, response);
		return null;
	}

	@RequestMapping("/update")
	public String update(TecInfo user, HttpServletResponse response) throws Exception {
		
		String hql = "from TecInfo as user where user.jobID = '" + user.getJobID() + "'";
		TecInfo o = tecService.getByHql(hql);
		
		if (o == null) {
			response.getWriter().write("<script>alert('更新失败，工号不存在！');");
		} else {
			if(o.getStatus() == 1){
				user.setStatus(new Integer(1));
				tecService.update(user);
			}else{
				user.setStatus(new Integer(0));
				tecService.update(user);
			}
			response.getWriter().write("<script>alert('更新成功！');");
		}
		response.getWriter().write("window.parent.closeCurTab();</script>");
		return null;
	}

	@RequestMapping("/register") // @RequestMapping用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径
	public String doregister(TecInfo teacher, HttpSession httpSession, Map<String, Object> map, HttpServletResponse response) throws Exception {
		if (teacher != null) {
			TecInfo users = new TecInfo();
			users = tecService.getTecInfoById(teacher.getJobID());
			if (users == null) {
				teacher.setStatus(new Integer(0));
				tecService.save(teacher);
				//httpSession.setAttribute("username", teacher.getUsername());
				System.out.println("注册成功！");
				response.getWriter().write("<script>alert('注册成功！');");
			} else {
				System.out.println("工号已存在！");
				response.getWriter().write("<script>alert('工号已注册！');");
				return "redirect:/View/registerT.jsp";
			}
			return "redirect:/";
		} else {
			return "redirect:/View/registerT.jsp";
		}
	}
}
