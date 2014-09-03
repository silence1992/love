package com.lhx.test.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TestMap {

	public static void main(String[] args) {
		Map<String,String> m = new HashMap<String,String>();
		m.put("key1","value1");
		m.put("key2", "value2");
		m.put("key3","value3");
//		Set<String> set = m.keySet();
//		Iterator<String> it = set.iterator();
//		while(it.hasNext()){
//			String str = it.next();
//			System.out.println(str+m.get(str));
//		}
		
		Set<Entry<String,String>> set = m.entrySet();
		Iterator<Entry<String,String>> it = set.iterator();
		while(it.hasNext()){
			Entry<String,String> me = it.next();
			if(me.getKey().equals("key1")){
				System.out.println(me.getValue());
			}
			if(me.getKey().equals("key2")){
				System.out.println(me.getValue());
			}
			if(me.getKey().equals("key3")){
				System.out.println(me.getValue());
			}
		}
	}

}
