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
 * ����Test�ļ�������û�м�¼��һ����¼��������¼һ������һ��ѡ����
 * @author dhm
 *
 */
public class ReadtxtTruth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BatchPro();
//		String rpath="D:\\ѧϰ��\\ʵ����\\RDF\\Data\\�������о�\\MV����\\test\\heih11.txt";
//		String wpath="D:\\ѧϰ��\\ʵ����\\RDF\\Data\\�������о�\\MV����\\test\\heih11after.txt";
//		Get(wpath,rpath);		
	}
	public static void BatchPro(){
		String filerpath="D:\\ѧϰ��\\ʵ����\\RDF\\Data\\�������о�\\MV����\\location\\lo3";
		String truthpath="D:\\ѧϰ��\\ʵ����\\RDF\\Data\\�������о�\\MV����\\location\\lotruthMV.txt";	
		try{
			FileWriter fw1 = new FileWriter(truthpath,true);
			BufferedWriter bw1 = new BufferedWriter(fw1);
			File[] files = new File(filerpath).listFiles();
			String rpath;
			String wpath;
			String filename;
			for(int i=0;i<files.length;i++){
				rpath=files[i].getPath();
				filename=files[i].getName();
				Get(rpath,filename,bw1);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	public static void Get(String rpath,String filename,BufferedWriter bw1){	
				
		try{
			FileReader fr = new FileReader(rpath);
			BufferedReader br = new BufferedReader(fr);
			String pre1=br.readLine();		//ָʾ���Ա�ʶ��
			String pre2;					//ָʾ��ͻ����
			String object;			
			while(pre1!=null){
				pre2=br.readLine();		
				HashSet aftdata=new HashSet();		//��¼one,oneԭʼ�м�¼
				ArrayList aftsame=new ArrayList();		//object�洢������ȥ���޳�ͻ	
				while(pre2!=null&&pre2.contains("<oneobject")==false){	
					object=GetObject(pre2);       //��Ԫ��ָ�ȥ��������Ϣ���object	
					aftsame.add(object);
					aftdata.add(pre2);		
					pre2=br.readLine();
				}
				if(aftdata.size()>1){	//��¼����1��
					if(PreSame(aftsame)==false){
//						Print(aftdata);		//�г�ͻ
						TruthMorjority(pre1,filename,aftsame,bw1);
						
//						truthwrite(bw1,aftsame,filename,pre1);
					}				
				}
				pre1=pre2;
			}
		}catch(IOException e){
			e.printStackTrace();
		}	
	}
	public static String GetObject(String pre){
		String[] ns=pre.split(" ",3);
		String object=ns[2];
		if(object.contains("^^<")){
			object=object.substring(0, object.indexOf("^"));
		}
		return object;
	}
	/*���б��е�objectֵ���жϣ�ȫ��һ������true���г�ͻ����false������д�����ݣ�*/
	public static boolean PreSame(ArrayList aftsame){
		int i=0,j=i+1;
		int length=aftsame.size();
		String a,b;
		while(i<length-1){	
			a=aftsame.get(i).toString();
			b=aftsame.get(j).toString();
			if(a.equals(b)==false){
				return false;
			}
			i++;			
		}
		return true;
	}
//	public static void Print(HashSet aftdata){
//		Iterator it=aftdata.iterator();
//		int i=1;
//		while(it.hasNext()){	
//			System.out.println(i+++" "+it.next());
//		}
//	}
	public static void Write(BufferedWriter bw,HashSet aftdata){
		try{			
			Iterator it=aftdata.iterator();
			int i=1;
			while(it.hasNext()){	
				bw.write(it.next().toString());
				bw.flush(); 
				bw.newLine();
//				System.out.println(i+++" "+it.next());
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	public static void Write(BufferedWriter bw,String pre){
		try{			
				bw.write(pre);
				bw.flush(); 
				bw.newLine();
//				System.out.println(i+++" "+it.next());
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	static void TruthMorjority(String pre1,String filename,ArrayList aftsame,BufferedWriter bw1){
		String predicate=pre1.split(" ")[1];
		HashMap<String,Integer> diff=Morjority(aftsame);
		String most=sortbyValue(diff);
		String after="<"+filename+"> "+predicate+" "+most;						
		Write(bw1,after);
		System.out.println(after);
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
		//��HASHMAP�е���������
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

