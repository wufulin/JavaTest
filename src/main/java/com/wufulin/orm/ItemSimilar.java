package com.wufulin.orm;

public class ItemSimilar {

	private int goodsId;
	private double similar;
	
	public ItemSimilar(int goodsId){
		this.goodsId=goodsId;
	}
	
	public ItemSimilar(int goodsId,double similar){
		this.goodsId=goodsId;
		this.similar=similar;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public double getSimilar() {
		return similar;
	}

	public void setSimilar(double similar) {
		this.similar = similar;
	}
	
}
