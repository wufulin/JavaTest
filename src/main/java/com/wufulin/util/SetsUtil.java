package com.wufulin.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetsUtil {

	/**
	 * 求A、B并集
	 * @param setA 集合A
	 * @param setB 集合B
	 * @return
	 */
	public static <T> Set<T> union(Set<T> setA,Set<T> setB){
		Set<T> setUnion=null;
		
		if(setA instanceof TreeSet){
			setUnion=new TreeSet<T>();
		}else if(setA instanceof HashSet){
			setUnion=new HashSet<T>();
		}else if(setA instanceof LinkedHashSet){
			setUnion=new LinkedHashSet<T>();
		}
		
		if(setA!=null){
			Iterator<T> iterA=setA.iterator();
			while(iterA.hasNext()){
				setUnion.add(iterA.next());
			}
		}
		
		if (setB!=null) {
			Iterator<T> iterB = setB.iterator();
			while (iterB.hasNext()) {
				setUnion.add(iterB.next());
			}
		}
		return setUnion;
	}
	
	/**
	 * 求B-A差集
	 * @param setA 集合A
	 * @param setB 集合B
	 * @return
	 */
	public static <T> Set<T> difference(Set<T> setA,Set<T> setB){
		Set<T> setDiff=null;
		T item=null;
		
		if(setA instanceof TreeSet){
			setDiff=new TreeSet<T>();
		}else if(setA instanceof HashSet){
			setDiff=new HashSet<T>();
		}else if(setA instanceof LinkedHashSet){
			setDiff=new LinkedHashSet<T>();
		}
		
		Iterator<T> iterB=setB.iterator();
		while(iterB.hasNext()){
			item=iterB.next();
			if(!setA.contains(item)){
				setDiff.add(item);
			}
		}
		
		return setDiff;
	}
	
	/**
	 * 求A、B交集
	 * @param setA 集合A
	 * @param setB 集合B
	 * @return
	 */
	public static <T> Set<T> intersection(Set<T> setA,Set<T> setB){
		Set<T> setIntersection=null;
		T item=null;
		
		if(setA instanceof TreeSet){
			setIntersection=new TreeSet<T>();
		}else if(setA instanceof HashSet){
			setIntersection=new HashSet<T>();
		}else if(setA instanceof LinkedHashSet){
			setIntersection=new LinkedHashSet<T>();
		}
		
		Iterator<T> iterA=setA.iterator();
		while(iterA.hasNext()){
			item=iterA.next();
			if(setB.contains(item)){
				setIntersection.add(item);
			}
		}
		
		return setIntersection;
	}
}
