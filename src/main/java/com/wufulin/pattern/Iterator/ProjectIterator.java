package com.wufulin.pattern.Iterator;

import java.util.ArrayList;
import java.util.List;

public class ProjectIterator implements IProjectIterator {

	private List<IProject> projectList=new ArrayList<IProject>();
	
	private int index=0;
	
	public ProjectIterator(List<IProject> projectList){
		this.projectList=projectList;
	}
	
	public boolean hasNext() {
		boolean result=true;
		if(this.index>=this.projectList.size() || 
		   this.projectList.get(index)==null){
			result=false;
		}
		return result;
	}

	public IProject next() {
		return this.projectList.get(this.index++);
	}

	public void remove() {
	}

}
