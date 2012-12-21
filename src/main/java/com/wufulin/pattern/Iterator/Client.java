package com.wufulin.pattern.Iterator;

public class Client {

	public static void main(String[] args){
		IProject project=new Project();
		for(int i=0;i<10;i++){
			project.add("The "+i+" project", i, i*10);
		}
		
		IProjectIterator projectIterator=project.iterator();
		while(projectIterator.hasNext()){
			IProject p=projectIterator.next();
			System.out.println(p.getProjectInfo());
		}
	}
}
