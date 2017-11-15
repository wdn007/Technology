package com.technology.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.technology.Model.NewsInfo;
import com.technology.Model.RaceInfo;
import com.technology.Service.NewsService;
import com.technology.Service.RaceService;

@Controller // 使用该注解标志它是一个控制器
@RequestMapping(value = "/Show") // 用来处理请求地址映射，value指请求的实际地址
public class ShowController extends BaseController {
	@Autowired // 使用@Autowired进行自动装配，而不需要get/set方法
	public RaceService raceService;

	@Autowired
	public NewsService newsService;

	@RequestMapping(value = "/indexShow.do")
	public String indexShow(HttpServletRequest request, HttpServletResponse response) {
		Integer a = new Integer(1);
		
		String hql1 = "from RaceInfo as raceinfo where raceinfo.applyStatus=" + a + "order by raceinfo.uploadTime desc";
		//String hql1 = "from RaceInfo as raceinfo order by raceinfo.uploadTime desc";
		List<RaceInfo> raceinfoList = (List<RaceInfo>) raceService.findLimit(hql1);
		request.setAttribute("raceinfoList", raceinfoList);

		String hql2 = "from NewsInfo as newsinfo where newsinfo.status=" + a + "order by newsinfo.newsTime desc";
		//String hql2 = "from NewsInfo as newsinfo order by newsinfo.newsTime desc";
		List<NewsInfo> newsinfoList = (List<NewsInfo>) newsService.findLimit(hql2);
		request.setAttribute("newsinfoList", newsinfoList);

		return "index";
	}
	

}
