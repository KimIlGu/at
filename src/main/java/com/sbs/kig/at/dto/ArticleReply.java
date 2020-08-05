package com.sbs.kig.at.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleReply {
	private int id;
	private String regDate;
	private String updateDate;
	private String delDate;
	private boolean displayStatus;
	private boolean delStatus;
	private String body;
	private int articleId;
}
