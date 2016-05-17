package mv1;

/**
 * @author dhm测试程序，统计字符次数
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TestMV {

	private static final HashMap String = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String,Integer> diff=Data();
		String LaN=sortbyValue(diff).toString();
		System.out.println(LaN);
	}
	private static HashMap<String, Integer> Data(){
//		String []a={
//				"Beijing","Beijingp","Beiping","haime","beijing","beijing"
//		};
		String []a={
				"aa","Aa","aA","aa","aab","bbA"
		};
		HashMap<String,Integer> diff=new HashMap();
		for(int i=0;i<a.length;i++){
			a[i]=a[i].toLowerCase();
			if(diff.containsKey(a[i])==false){
				diff.put(a[i], 1);
			}
			else{
				int num=diff.get(a[i])+1;
				diff.put(a[i], num);
			}
		}
		return diff;
	}
	public static Object sortbyValue(Map<String,Integer> map){
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
		Iterator iter = ID.iterator(); 
//		while(iter.hasNext()) 
//		{
//		   Map.Entry entry = (Map.Entry)iter.next();
//		   String  key = (String)entry.getKey();
//		   System.out.println(key.toString()+" "+map.get(key));  
//		}
		return iter.next();
	}
}
