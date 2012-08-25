package com.wufulin.util.json;

import com.wufulin.util.json.JSONException;

public class JSONObjectDemo {

	public static void main(String[] args) throws JSONException
	{
		JSONObject jsonobj=new JSONObject("{'name':'wufulin','age':20}");
		String name=jsonobj.getString("name");
		int age=jsonobj.getInt("age");
		System.out.println(name+": "+age);
	}
}
