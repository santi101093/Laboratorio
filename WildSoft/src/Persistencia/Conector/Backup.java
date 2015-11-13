package Persistencia.Conector;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Backup {

	private static void backup() {
		   try {
		      Process p = Runtime
		            .getRuntime()
		            .exec("C:/Program Files/MySQL/MySQL Server 5.7/bin/mysql -u root -ppassword root");
		 
		      InputStream is = p.getInputStream();
		      FileOutputStream fos = new FileOutputStream("Wildsoft.sql");
		      byte[] buffer = new byte[1000];
		 
		      int leido = is.read(buffer);
		      while (leido > 0) {
		         fos.write(buffer, 0, leido);
		         leido = is.read(buffer);
		      }
		 
		      fos.close();
		 
		   } catch (Exception e) {
		      e.printStackTrace();
		   }
		}
	
	
	
	private static void restore() {
		   try {
		      Process p = Runtime
		            .getRuntime()
		            .exec("C:/Program Files/MySQL/MySQL Server 5.7/bin/mysql -u root -ppassword root");
		 
		      OutputStream os = p.getOutputStream();
		      FileInputStream fis = new FileInputStream("Wildsoft.sql");
		      byte[] buffer = new byte[1000];
		 
		      int leido = fis.read(buffer);
		      while (leido > 0) {
		         os.write(buffer, 0, leido);
		         leido = fis.read(buffer);
		      }
		 
		      os.flush();
		      os.close();
		      fis.close();
		 
		   } catch (Exception e) {
		      e.printStackTrace();
		   }
		}
	
	
	
	
}
