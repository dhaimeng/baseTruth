package mv1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * 测试文件
 * one one truth三元组存新文件
 * @author dhm
 *
 */
public class Truth {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList aftsame=new ArrayList();
		aftsame.add("Beijing");
		aftsame.add("Beijingp");
		aftsame.add("Beijing");
		aftsame.add("Beijing");
		aftsame.add("Beijingp");
//		System.out.println(aftsame.size());	
//		for(int i=0;i<aftsame.size();i++){
//			System.out.println(aftsame.get(i));
//		}
		HashMap diff=Morjority(aftsame);
		String pre=sortbyValue(diff);
		System.out.println(pre);
	}

	static HashMap<String, Integer> Morjority(ArrayList aftsame){
		HashMap<String,Integer> diff=new HashMap();
		int i=0;
		int length=aftsame.size();
		String a;
		while(i<length){	
			a=aftsame.get(i).toString();
			if(diff.containsKey(a)==false){
				diff.put(a, 1);
			}
			else{
				int num=diff.get(a)+1;
				diff.put(a, num);
			}
			i++;
		}
		return diff;
	}
	public static String sortbyValue(Map<String,Integer> map){
		ArrayList<Map.Entry<String,Integer>> ID=new ArrayList<Map.Entry<String, Integer>>(map.entrySet());		
		Collections.sort(ID, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		    	if ((o2.getValue() - o1.getValue())>0)  
		            return 1;  
		          else if((o2.getValue() - o1.getValue())==0)  
		            return 0;  
		          else   
		            return -1;  
		    }
		});
		//将HASHMAP中的数据排序
//		for (Iterator iter = ID.iterator(); iter.hasNext();) 
//		{
//		   Map.Entry entry = (Map.Entry)iter.next();
//		   String  key = (String)entry.getKey();
//		   System.out.println(key.toString()+" "+map.get(key));  
//		}
		Iterator it = ID.iterator();
		Map.Entry entry = (Map.Entry)it.next();
		String  key = (String)entry.getKey();
//		System.out.println(key.toString()+" "+map.get(key));
		String pre=key.toString()+" "+map.get(key);
		return pre;
	}
}
