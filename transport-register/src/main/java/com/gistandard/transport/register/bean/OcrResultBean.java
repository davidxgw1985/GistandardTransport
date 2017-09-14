package com.gistandard.transport.register.bean;

import java.io.Serializable;

public class OcrResultBean implements Serializable{

	// ocr识别身份证号
	private String identno;

	// ocr识别真实名称
	private String realName;

	// 识别出的头像的ID
	private Integer portraitFileId;

	// 识别出头像照片url
	private String portraitUrl;

	// 真实头像照片
	private String userImg;

	public String getIdentno() {
		return identno;
	}

	public void setIdentno(String identno) {
		this.identno = identno;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getPortraitFileId() {
		return portraitFileId;
	}

	public void setPortraitFileId(Integer portraitFileId) {
		this.portraitFileId = portraitFileId;
	}

	public String getPortraitUrl() {
		return portraitUrl;
	}

	public void setPortraitUrl(String portraitUrl) {
		this.portraitUrl = portraitUrl;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
}
