package com.wufulin.pattern.Iterator;

import java.util.ArrayList;
import java.util.List;

public class Project implements IProject {

	private static List<IProject> projectList = new ArrayList<IProject>();

	private String name = "";
	private int num = 0;
	private int cost = 0;

	public Project() {

	}

	private Project(String name, int num, int cost) {
		this.name = name;
		this.num = num;
		this.cost = cost;
	}

	public void add(String name, int num, int cost) {
		projectList.add(new Project(name,num,cost));
	}

	public String getProjectInfo() {
		String info="";
		info=info+"name: "+this.name;
		info=info+"\tnum: "+this.num;
		info=info+"\tcost: "+this.cost;
		return info;
	}

	public IProjectIterator iterator() {
		return new ProjectIterator(projectList);
	}

}
