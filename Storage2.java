package mv1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		connDatabase();
		Statement stmt=connDatabase();
		readtxt(stmt);
	}
	public static Statement connDatabase(){		
		// TODO Auto-generated method stub
		Connection conn = null;	
		String url = "jdbc:mysql://localhost/btc2012?"
                + "user=root&password=haimeng&useUnicode=true&characterEncoding=UTF8";
		Statement stmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("成功加载MySQL驱动程序");
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
//			String content=readtxt();
//			writeData(stmt);
		}catch(Exception e){
			System.out.println("MySQL操作错误");
			e.printStackTrace();
		}
		return stmt;
	}
	public static void writeData(Statement stmt,String content){
		String[] consplit=content.split("> ");
		String s=consplit[1];
		String p=consplit[2];
		String o=consplit[3];
		String l=consplit[0];
//		System.out.println(s+"@"+p+"@"+o+"@"+l);
		try{								
			//String sql="insert into student (name,num,age) values ('"+name+"',"+num+","+age+")";
			String sql="insert into benchmark(Subject,Predicate,Object,lable) values('"+s.replace("<", "")+"',"+"'"+p.replace("<", "")+"',"+"'"+o+"',"+"'"+l.replace("<", "")+"')";
			stmt.executeUpdate(sql);
		}catch(Exception e){
			System.out.println("MySQL操作错误");
			e.printStackTrace();
		}
	}
	public static void readtxt(Statement stmt){
		String read="D:\\学习中\\实验室\\RDF\\Data\\split\\test";			
		File[] files = new File(read).listFiles();
		for(int i = 0; i < files.length; i++){
			String rpath=files[i].getPath();
			try{
				FileReader fr = new FileReader(rpath);
				BufferedReader br = new BufferedReader(fr);
				String pre=br.readLine();
				while(pre!=null){
					while(pre.contains("<oneobject")){
//						System.out.println(pre);			
						String[] sp=pre.split(" ");
						String lable=sp[1];	
						boolean Eng=true;
						pre=br.readLine();
						while(pre.contains("<oneobject")==false){
							if(lable.equals("<lable>")){
								Eng=multilanguage(pre);
//								System.out.println(pre);	
							}
							if(Eng==true){
								String wdb=lable+" "+pre;
//								writeData(stmt,wdb);
								System.out.println(wdb);
							}
							pre=br.readLine();						
							if(pre==null){
								break;
							}
						}							
					}
					pre=br.readLine();
				}			
			}catch(Exception e){
				System.out.println("somelines sql wrong!");
			}
		}

	}
	static boolean multilanguage(String pre){
		if(pre.contains("@")){
			if(pre.contains("en")){
				return true;
			}else{
				return false;
			}			
		}
		else{
			return true;
		}
	}

}
