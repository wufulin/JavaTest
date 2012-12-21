package com.wufulin.orm;

import java.util.Comparator;

public class UserSimilarComparator implements Comparator<UserSimilar>{

	public int compare(UserSimilar u1, UserSimilar u2) {
		double s1=u1.getSimilar();
		double s2=u2.getSimilar();
		if(s1<s2){
			return 1;
		}else if(s1==s2){
			return 0;
		}
		return -1;
	}

}
