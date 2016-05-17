package mv1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 测试Test文件，处理没有记录，一条记录，多条记录一样，不一样选多数
 * @author dhm
 *
 */
public class Readtxt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BatchPro();
//		String rpath="D:\\学习中\\实验室\\RDF\\Data\\处理方法研究\\MV方法\\test\\heih11.txt";
//		String wpath="D:\\学习中\\实验室\\RDF\\Data\\处理方法研究\\MV方法\\test\\heih11after.txt";
//		Get(wpath,rpath);		
	}
	public static void BatchPro(){
		String filerpath="D:\\学习中\\实验室\\RDF\\Data\\处理方法研究\\MV方法\\location\\lo3";
		String filewpath="D:\\学习中\\实验室\\RDF\\Data\\处理方法研究\\MV方法\\location\\lo4";
		String truthpath="D:\\学习中\\实验室\\RDF\\Data\\处理方法研究\\MV方法\\location\\lotruth.txt";	
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
				wpath=filewpath+"\\"+filename;
				Get(wpath,rpath,filename,bw1);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	public static void Get(String wpath,String rpath,String filename,BufferedWriter bw1){	
				
		try{
			FileReader fr = new FileReader(rpath);
			BufferedReader br = new BufferedReader(fr);
			String pre1=br.readLine();		//指示属性标识符
			String pre2;					//指示冲突数据
			String object;
			FileWriter fw = new FileWriter(wpath,false);
			BufferedWriter bw = new BufferedWriter(fw);
			
			while(pre1!=null){
				pre2=br.readLine();		
				HashSet aftdata=new HashSet();		//记录one,one原始行记录
				ArrayList aftsame=new ArrayList();		//object存储，用于去除无冲突	
				while(pre2!=null&&pre2.contains("<oneobject")==false){	
					object=GetObject(pre2);       //三元组分割去除无用信息后的object	
					aftsame.add(object);
					aftdata.add(pre2);		
					pre2=br.readLine();
				}
				if(aftdata.size()>1){	//记录多于1条
					if(PreSame(aftsame)==false){
//						Print(aftdata);		//有冲突
						
						Write(bw,pre1);
						Write(bw,aftdata);
//						Write(bw1,"dsagag");
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
	/*对列表中的object值作判断，全部一样返回true，有冲突返回false（保留写的数据）*/
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
//	public static void truthwrite(BufferedWriter bw1,ArrayList aftsame,String filename,String pre1){
//		HashMap<String,Integer> diff=Truth.Morjority(aftsame);
//		String most=Truth.sortbyValue(diff);
//		String entity=filename.substring(0, filename.length()-5);
//		String predicate=pre1.split(" ")[1];
//		String truthtriple="<"+entity+">"+" "+predicate+" "+most;
//		Write(bw1,truthtriple);
//	}
}

