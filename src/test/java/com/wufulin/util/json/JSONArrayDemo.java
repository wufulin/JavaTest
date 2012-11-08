package com.wufulin.util.json;

import com.wufulin.util.json.JSONArray;
import com.wufulin.util.json.JSONException;

public class JSONArrayDemo {

	/**
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JSONException {
		
//		Vector<String> vector=new Vector<String>();
//		vector.add("aabbvv");
//		System.out.println("String : "+vector.toString()+" Size : "+vector.toString().getBytes().length);
		JSONArray jsonarray=new JSONArray("[{'name':'wufulin1','age':21},{'name':'wufulin2','age':22}]");
		for(int i=0;i<jsonarray.length();i++){
			String name=jsonarray.getJSONObject(i).getString("name");
			int age=jsonarray.getJSONObject(i).getInt("age");
			System.out.println("name= "+name+"  "+"age= "+age);
		}
	}

}
