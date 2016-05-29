package com.wwb.demo.utils.schedular;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wwb.demo.utils.CommonUtils;
import com.wwb.demo.utils.constance.Constance;

@Component
public class RomoveJob implements CronJob{

	// run every 
	@Scheduled(cron="0 0 1 * * ?")
	@Override
	public void execute(){
        try{  
        	String yesterday = CommonUtils.getDate(-1);
        	String path = Constance.pic + yesterday;
        	deleteFile(path);
        }catch(Exception ex){  
             ex.printStackTrace();  
        }  
   }  
	
	public static boolean deleteFile(String delpath) throws Exception {  
		  try {  
		  
		   File file = new File(delpath);  
		   // 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true  
		   if (!file.isDirectory()) {  
		    file.delete();  
		   } else if (file.isDirectory()) {  
		    String[] filelist = file.list();  
		    for (int i = 0; i < filelist.length; i++) {  
		     File delfile = new File(delpath + "\\" + filelist[i]);  
		     if (!delfile.isDirectory()) {  
		      delfile.delete();  
		      System.out  
		        .println(delfile.getAbsolutePath() + "删除文件成功");  
		     } else if (delfile.isDirectory()) {  
		      deleteFile(delpath + "\\" + filelist[i]);  
		     }  
		    }  
		    System.out.println(file.getAbsolutePath()+"删除成功");  
		    file.delete();  
		   }  
		  
		  } catch (FileNotFoundException e) {  
		   System.out.println("deletefile() Exception:" + e.getMessage());  
		  }  
		  return true;  
		 }
}
