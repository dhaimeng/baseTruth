package mv1;

import java.io.BufferedReader;
import java.io.FileReader;

public class Test {
	static String aa="u74DCu7D0Du83EFu6258u5DDE";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String pre="<http://www.wikidata.org/entity/Q130742> <http://schema.org/name> 798 <hk daguadg>";
//		String[] ns=pre.split(" ", 3);
//		System.out.println(ns.length);
//		for(int i=0;i<ns.length;i++){
//			System.out.println(ns[i]);
//		}
		System.out.println(aa.length());
		if(aa.startsWith("u")&&aa.length()>=30){
			System.out.println(true+" "+aa.length());
		}
	}
	public static void lowertest(){
		String rpath="D:\\学习中\\实验室\\RDF\\Data\\处理方法研究\\MV方法\\test1.txt";		
		try{
			FileReader fr = new FileReader(rpath);
			BufferedReader br = new BufferedReader(fr);
			String pre1=br.readLine();		//指示属性标识符
			String source,object;
			int m,n,p;
			while(pre1!=null){
				System.out.println(pre1);
				if(pre1.contains("> .")){
//					String ns=pre1.split(regex, limit);
//					m=pre1.indexOf(ch)
//					source=pre1.substring(beginIndex, endIndex);
//					object=pre1.substring(beginIndex)
				}
				
			}
		}catch(Exception e){
			System.out.println("Read Wrong");
		}
	}
	public int indexOf(String s1, String s2, int time){
        if(time == 1)
            return s1.indexOf(s2);
        else
            return s1.indexOf(s2, indexOf(s1, s2, time - 1) + 1);
    }

}
