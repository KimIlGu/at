package com.sbs.kig.at.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.kig.at.dao.ArticleDao;
import com.sbs.kig.at.dto.Article;
import com.sbs.kig.at.dto.ArticleReply;
import com.sbs.kig.at.util.CUtil;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	public int getTotalCount(String searchKeywordType, String searchKeyword) {
		return articleDao.getTotalCount(searchKeywordType, searchKeyword);
	}

	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}

	public Map<String, Object> add(Map<String, Object> param) {
		articleDao.add(param);
		
		int id = CUtil.getAsInt(param.get("id"));
		Map<String, Object> rs = new HashMap<>();

		rs.put("resultCode", "S-1");
		rs.put("msg", String.format("%d번 게시물이 생성되었습니다.", id));

		return rs;
	}

	public Map<String, Object> modifyArticle(Map<String, Object> param) {
		articleDao.modifyArticle(param);
		
		int id = CUtil.getAsInt(param.get("id"));
		Map<String, Object> rs = new HashMap<>();

		rs.put("resultCode", "S-1");
		rs.put("msg", String.format("%d번 게시물이 수정되었습니다.", id));

		return rs;
	}

	public Map<String, Object> deleteArticle(int id) {
		articleDao.deleteArticle(id);
		
		Map<String, Object> rs = new HashMap<>();

		rs.put("resultCode", "S-1");
		rs.put("msg", String.format("%d번 게시물이 삭제되었습니다.", id));

		return rs;
	}

	public void hitUp(int id) {
		articleDao.hitUp(id);
	}
	
	public List<Article> getForPrintArticles(int page, int itemsInAPage, String searchKeywordType, String searchKeyword) {
		return articleDao.getForPrintArticles(page, itemsInAPage, searchKeywordType, searchKeyword); 
	}

	public Article getArticleByNext(int id) {
		return articleDao.getArticleByNext(id); 
	}

	public Article getArticleByPrev(int id) {
		return articleDao.getArticleByPrev(id);
	}

	public Map<String, Object> writeReply(Map<String, Object> param) {
		articleDao.writeArticleReply(param);
		
		int id = CUtil.getAsInt(param.get("id"));
		
		Map<String, Object> rs = new HashMap<>();

		rs.put("resultCode", "S-1");
		rs.put("msg", String.format("%d번 게시물 댓글이 생성되었습니다.", id) );
		
		return rs;
	}
	
	public List<ArticleReply> getForPrintArticleReplies(int articleId) {
		return articleDao.getForPrintArticleReplies(articleId);
	}

	public Map<String, Object> deleteArticleReply(int id) {
		articleDao.deleteArticleReply(id);
		
		Map<String, Object> rs = new HashMap<>();

		rs.put("resultCode", "S-1");
		rs.put("msg", String.format("%d번 게시물 댓글이 삭제되었습니다.", id));

		return rs;
	}

	public ArticleReply getForPrintArticleReply(int id) {
		return articleDao.getForPrintArticleReply(id);
	}

	public Map<String, Object> modifyReply(Map<String, Object> param) {
		articleDao.modifyArticleReply(param);
		
		int id = CUtil.getAsInt(param.get("id"));
		Map<String, Object> rs = new HashMap<>();

		rs.put("resultCode", "S-1");
		rs.put("msg", String.format("%d번 게시물 댓글이 수정되었습니다.", id));
		
		return rs;
	}
	
	
}