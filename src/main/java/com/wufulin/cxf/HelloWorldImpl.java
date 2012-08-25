package com.wufulin.cxf;

public class HelloWorldImpl implements HelloWorld {

	public String sayHi(String name) {
		String msg = "hello " + name + "!";
		return msg;
	}

}
