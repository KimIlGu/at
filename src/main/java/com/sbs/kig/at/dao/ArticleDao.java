package com.sbs.kig.at.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.kig.at.dto.Article;
import com.sbs.kig.at.dto.ArticleReply;

@Mapper
public interface ArticleDao {

	Article getArticle(@Param("id") int id);

	void add(Map<String, Object> param);

	Map<String, Object> modifyArticle(Map<String, Object> param);

	void deleteArticle(@Param("id") int id);

	void hitUp(long id);
	
	int getTotalCount(String searchKeywordType, String searchKeyword);

	List<Article> getForPrintArticles(@Param("id") int page, @Param("id") int itemsInAPage, String searchKeywordType, String searchKeyword);

	Article getArticleByNext(long id);

	Article getArticleByPrev(long id);

	void writeArticleReply(Map<String, Object> param);

	List<ArticleReply> getForPrintArticleReplies(@Param("articleId") int articleId);

	void deleteArticleReply(int id);

	ArticleReply getForPrintArticleReply(int id);

	void modifyArticleReply(Map<String, Object> param);

	
}