package com.wufulin.cxf;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class Client {

	public static void main(String[] args) throws Exception {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(HelloWorld.class);
		factory.setAddress("http://localhost:9000/ws/HelloWorld");
		HelloWorld helloworld = (HelloWorld)factory.create();
		System.out.println(helloworld.sayHi("wufulin"));
		System.exit(0);
	}
}
