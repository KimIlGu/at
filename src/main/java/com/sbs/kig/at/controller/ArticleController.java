package com.sbs.kig.at.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.kig.at.dto.Article;
import com.sbs.kig.at.dto.ArticleReply;
import com.sbs.kig.at.service.ArticleService;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/article/add")
	public String showAdd() {
		return "article/add";
	}
	
	@RequestMapping("/article/doAdd")
	public String showDoAdd(Model model, @RequestParam Map<String, Object> param) {
		Map<String, Object> rs = articleService.add(param);
		
		String msg = (String) rs.get("msg");
		String redirectUrl = "/article/list";
		
		model.addAttribute("alertMsg", msg);
		model.addAttribute("locationReplace", redirectUrl);

		return "common/redirect";
	}
	
	@RequestMapping("/article/modify")
	public String showModify(Model model, int id) {
		Article article = articleService.getArticle(id);
		
		model.addAttribute("article", article);

		return "article/modify";
	}
	
	@RequestMapping("/article/doModify")
	public String showDoModify(Model model, @RequestParam Map<String, Object> param) {
		Map<String, Object> rs = articleService.modifyArticle(param);

		int id = Integer.parseInt((String) param.get("id"));

		String msg = (String) rs.get("msg");
		String redirectUrl = "/article/detail?id=" + id;

		model.addAttribute("alertMsg", msg);
		model.addAttribute("locationReplace", redirectUrl);

		return "common/redirect";
	}
	
	@RequestMapping("/article/doDelete")
	public String showDoDelete(Model model, int id) {
		Map<String, Object> rs = articleService.deleteArticle(id);
		
		String msg = (String)rs.get("msg");
		String redirectUrl = "/article/list";

		model.addAttribute("alertMsg", msg);
		model.addAttribute("locationReplace", redirectUrl);

		return "common/redirect";
	}
	
	@RequestMapping("/article/list")
	public String showList(@RequestParam(value = "page", required = true, defaultValue = "1") int page, 
			@RequestParam(defaultValue = "title") String searchKeywordType, 
			@RequestParam(defaultValue = "") String searchKeyword, Model model) {		
		
		int itemsInAPage = 10;
		
		int totalCount = articleService.getTotalCount(searchKeywordType, searchKeyword); 
		int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);
		
		List<Article> articles = articleService.getForPrintArticles(page, itemsInAPage, searchKeywordType, searchKeyword);
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("page", page);
		model.addAttribute("articles", articles);
		
		return "article/list";
	}
	
	@RequestMapping("/article/detail")
	public String showDetail(Model model, int id) {
		Article article = articleService.getArticle(id);
		
		Article next = articleService.getArticleByNext(id);
		Article prev = articleService.getArticleByPrev(id);
		
		articleService.hitUp(id);
		
		model.addAttribute("article", article);
		model.addAttribute("next", next);
		model.addAttribute("prev", prev);
		
		List<ArticleReply> articleReplies = articleService.getForPrintArticleReplies(article.getId());

		model.addAttribute("articleReplies", articleReplies);
		
		return "article/detail";
	}
	
	@RequestMapping("article/doWriteReply")
	public String doWriteReply(Model model, @RequestParam Map<String, Object> param) {

		Map<String, Object> rs = articleService.writeReply(param);

		String msg = (String) rs.get("msg");
		String redirectUrl = (String) param.get("redirectUrl");

		model.addAttribute("alertMsg", msg);
		model.addAttribute("locationReplace", redirectUrl);

		return "common/redirect";
	}
	
	@RequestMapping("article/doDeleteReply")
	public String doDeleteReply(Model model, int id, String redirectUrl) {

		Map<String, Object> rs = articleService.deleteArticleReply(id);

		String msg = (String) rs.get("msg");

		model.addAttribute("alertMsg", msg);
		model.addAttribute("locationReplace", redirectUrl);

		return "common/redirect";
	}
	
	@RequestMapping("article/modifyReply")
	public String showModifyReply(Model model, int id) {

		ArticleReply articleReply = articleService.getForPrintArticleReply(id);

		model.addAttribute("articleReply", articleReply);

		return "article/modifyReply";
	}
	
	@RequestMapping("article/doModifyReply")
	public String doModifyReply(Model model, @RequestParam Map<String, Object> param) {

		Map<String, Object> rs = articleService.modifyReply(param);

		String msg = (String) rs.get("msg");
		String redirectUrl = (String) param.get("redirectUrl");

		model.addAttribute("alertMsg", msg);
		model.addAttribute("locationReplace", redirectUrl);

		return "common/redirect";
	}
	
	@RequestMapping("article/doWriteReplyAjax")
	@ResponseBody
	public Map<String, Object> doWriteReplyAjax(@RequestParam Map<String, Object> param) {
		Map<String, Object> rs = articleService.writeReply(param);

		return rs;
	}
	

}
