package com.sbs.kig.at.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleReply {
	private int id;
	private String regDate;
	private String body;
	private int articleId;
}