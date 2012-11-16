package com.wufulin.orm.entity;

public class ShareGoodsLike {

	private int id;
	private int userId;
	private int goodsId;
	private short commentType;
	private long addtime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public short getCommentType() {
		return commentType;
	}
	public void setCommentType(short commentType) {
		this.commentType = commentType;
	}
	public long getAddtime() {
		return addtime;
	}
	public void setAddtime(long addtime) {
		this.addtime = addtime;
	}
	
}
