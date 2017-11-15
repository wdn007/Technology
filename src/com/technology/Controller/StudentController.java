package com.technology.Controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.technology.Model.Grid;
import com.technology.Model.Json;
import com.technology.Model.StuInfo;
import com.technology.Model.TecInfo;
import com.technology.Service.StudentService;
import com.technology.Service.TecService;

//@SessionAttributes({ "username", "identity" })
@Controller // 使用该注解标志它是一个控制器
@RequestMapping(value = "/Student") // 用来处理请求地址映射，value指请求的实际地址
public class StudentController extends BaseController {

	@Autowired // 使用@Autowired进行自动装配，而不需要get/set方法
	public StudentService studentService;
	@Autowired
	public TecService teacherService;

	@RequestMapping("/list")
	public ModelAndView goList() {
		return new ModelAndView("student/list");
	}

	@RequestMapping("/listStuInfo")
	public String listStuInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);// 默认值为1
		int rows = ServletRequestUtils.getIntParameter(request, "rows", 0);
		String queryStudentID = request.getParameter("queryStudentID");// 获取要查询的用户账号
		String queryStuName = request.getParameter("queryStuName");// 获取要查询的用户姓名
		Grid grid = new Grid();
		String hql;
		List<StuInfo> users;
		if ((queryStudentID != null && !"".equals(queryStudentID))
				&& (queryStuName != null && !"".equals(queryStuName))) {
			hql = "from StuInfo as user where user.studentID like '%" + queryStudentID + "%' and user.stuName like '%"
					+ queryStuName + "%'";
		} else if ((queryStudentID != null && !"".equals(queryStudentID))
				&& (queryStuName == null || "".equals(queryStuName))) {
			hql = "from StuInfo as user where user.studentID like '%" + queryStudentID + "%'";
		} else if ((queryStudentID == null || "".equals(queryStudentID))
				&& (queryStuName != null && !"".equals(queryStuName))) {
			hql = "from StuInfo as user where user.stuName like '%" + queryStuName + "%'";
		} else {
			hql = "from StuInfo";
		}
		users = (List<StuInfo>) studentService.find(hql, page, rows);
		grid.setTotal(studentService.count("select count(*) " + hql));
		grid.setRows(users);
		writeJson(grid, response);
		return null;
	}

	@RequestMapping("/addStuInfo")
	public String addStuInfo(StuInfo user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Json json = new Json();// 用于向前端发送消息
		String hql = "from StuInfo as user where user.studentID = " + user.getStudentID();
		if (studentService.getByHql(hql) != null) {
			json.setMsg("新建用户失败，该用户已存在！");
		} else {
			studentService.save(user);
			json.setMsg("新建用户成功！");
			json.setSuccess(true);
		}
		writeJson(json, response);
		return null;
	}

	@RequestMapping("/removeStuInfo")
	public String removeStuInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Json json = new Json();// 用于向前端发送消息
		String userId = request.getParameter("studentID");
		try {
			studentService.delete(studentService.getStuInfoById(userId));
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

	@RequestMapping("/updateStuInfo")
	public String updateStuInfo(StuInfo user, HttpServletResponse response) throws Exception {
		Json json = new Json();// 用于向前端发送消息
		String hql = "from StuInfo as user where user.studentID = '" + user.getStudentID() + "'";
		if (studentService.getByHql(hql) == null) {
			json.setMsg("更新失败，学号不存在！");
		} else {
			studentService.update(user);
			json.setMsg("更新成功！");
			json.setSuccess(true);
		}
		writeJson(json, response);
		return null;
	}

	/**
	 * 查询所有
	 * 
	 * @param id
	 */
	@RequestMapping("/getStuInfo")
	@ResponseBody
	public List<StuInfo> getStuInfo() {
		return studentService.getStuInfo();
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getStuInfoById")
	@ResponseBody
	public StuInfo getStuInfoById(String id) {
		return studentService.getStuInfoById(id);
	}

	/**
	 * 登录请求
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login") // @RequestMapping用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径
	public String dologin(String identity, String ID, String password, HttpSession httpSession,
			final RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response)  throws Exception {
		StuInfo student = new StuInfo();
		TecInfo teacher = new TecInfo();
		if (identity.equals("1")) {
			if ("admin".equals(ID) && "admin".equals(password)) {
				httpSession.setAttribute("ID", "admin");
				httpSession.setAttribute("username", "系统/竞赛管理员");
				return "redirect:/View/admin.jsp";
			} else {
				student = studentService.getStuInfoById(ID);
				if (student != null) {
					if(student.getStatus() == 1){
						if (student.getPassword().equals(password)) {
							httpSession.setAttribute("ID", student.getStudentID());
							httpSession.setAttribute("username", student.getStuName());
							response.getWriter().write("<script>alert('密码匹配成功！');");
							System.out.println("密码匹配成功！");
							return "redirect:/";
						} else {
							System.out.println("密码匹配不成功！");
							response.getWriter().write("<script>alert('密码错误！');");
						}
					}else {
						System.out.println("账号未通过审核！");
						response.getWriter().write("<script>alert('账号未通过审核！');");
					}
				}else{
					System.out.println("学号不存在！");
					response.getWriter().write("<script>alert('学号未注册！');");
				}	
			}
		} else if (identity.equals("2")) {
			if ("admin".equals(ID) && "admin".equals(password)) {
				httpSession.setAttribute("ID", "admin");
				httpSession.setAttribute("username", "系统/竞赛管理员");
				return "redirect:/View/admin.jsp";
			} else {
				teacher = teacherService.getTecInfoById(ID);
				if (teacher != null) {
					//System.out.println(teacher.getStatus());
					if(teacher.getStatus() == 1){
						if (teacher.getPassword().equals(password)) {
							httpSession.setAttribute("ID", teacher.getJobID());
							httpSession.setAttribute("username", teacher.getUsername());
							System.out.println("密码匹配成功！");
							response.getWriter().write("<script>alert('密码匹配成功！');");
							return "redirect:/View/adminTeacher.jsp";
						} else {
							System.out.println("密码匹配不成功！");
							response.getWriter().write("<script>alert('密码错误！');");
						}
					} else {
					System.out.println("账号未通过审核！");
					response.getWriter().write("<script>alert('账号未通过审核！');");
				}
			}else{
				System.out.println("教师工号不存在！");
				response.getWriter().write("<script>alert('密码匹配成功！');");
			}
		}
	  }
		return "redirect:/View/login.jsp";
	}

	@RequestMapping("/logout") // @RequestMapping用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径
	public String dologout(HttpSession httpSession) {
		httpSession.removeAttribute("ID");
		httpSession.removeAttribute("username");
		return "redirect:/";
	}

	/**
	 * 注册请求
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/register") // @RequestMapping用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径
	public String doregister(StuInfo student, HttpSession httpSession, Map<String, Object> map) {
		if (student != null) {
			StuInfo users = new StuInfo();
			users = studentService.getStuInfoById(student.getStudentID());
			if (users == null) {
				student.setStatus(new Integer(0));
				studentService.addStuInfo(student);
				//httpSession.setAttribute("username", student.getStuName());
				System.out.println("注册成功！");
			} else {
				System.out.println("学号已存在！");
				return "redirect:/View/register.jsp";
			}
			return "redirect:/";
		} else {
			return "redirect:/View/register.jsp";
		}
	}

	/**
	 * 删除一条数据
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteStuInfoById")
	public String deleteStuInfoById(@RequestParam(value = "id") String id) {
		System.out.println("删除成功！");
		studentService.deleteStuInfoById(id);
		return "redirect:main";
	}

	/**
	 * 跳转到更新页面，回显数据 editpage.jsp
	 * 
	 * @param id
	 * @param model
	 *            使用的Model保存回显数据
	 * @return
	 */
	@RequestMapping(value = "/doupdate")
	public String doupdate(@RequestParam(value = "id") String id, Model model) {
		model.addAttribute("person", studentService.getStuInfoById(id));
		return "editpage";
	}

	/**
	 * 更新数据
	 * 
	 * @param person
	 * @return
	 */
	@RequestMapping(value = "/updateperson")
	public String updateperson(StuInfo user) {
		System.out.println(user.toString());
		studentService.updateStuInfo(user);
		return "redirect:main";
	}

	/**
	 * 查询所有人员信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/main")
	public String mian(Map<String, Object> map) {
		map.put("stuInfolist", studentService.getStuInfo());
		/*
		 * 遍历集合，查看查询到的数据 List<Person> lists = personService.getPersons();
		 * Iterator<Person> it = lists.iterator(); while(it.hasNext()){ Person p
		 * =it.next(); System.out.println(p.toString()); }
		 */
		return "main";
	}

}
