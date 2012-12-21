package com.wufulin.orm;

import java.util.Comparator;

public class ItemSimilarComparator implements Comparator<ItemSimilar>{

	public int compare(ItemSimilar item1, ItemSimilar item2) {
		double similar1=item1.getSimilar();
		double similar2=item2.getSimilar();
		if(similar1<similar2){
			return 1;
		}else if(similar1==similar2){
			return 0;
		}else{
			return -1;
		}
	}

}
